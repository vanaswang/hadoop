package com.atguigu.interceptor;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-05-05 2:06 下午
 */
public class MySink extends AbstractSink implements Configurable {
    /**
     * 该方法被调用时，会从Channel中拉取数据并处理
     *
     * @return 处理的状态
     * @throws EventDeliveryException 处理失败时候会抛出该异常
     */
    public Status process() throws EventDeliveryException {
        Status status = null;

        // Start transaction 开启事务
        Channel ch = getChannel();
        Transaction txn = ch.getTransaction();
        txn.begin();
        try {
            // This try clause includes whatever Channel operations you want to do
//            拉取数据，如果拉不到event是null，
            Event event;
            while ((event = ch.take()) == null) {
                Thread.sleep(100);
            }


//            若果成功拉到数据
            // Send the Event to the external repository.
            storeSomeData(event);

            txn.commit();
            status = Status.READY;
        } catch (Throwable t) {
            txn.rollback();

            // Log exception, handle individual exceptions as needed

            status = Status.BACKOFF;

            // re-throw all Errors
            if (t instanceof Error) {
                throw (Error) t;
            }
        } finally {
            txn.close();
        }
        return status;
    }

    /**
     * 要储存的数据
     *
     * @param event
     */
    private void storeSomeData(Event event) throws IOException {
//       将数据打印到控制台
        byte[] body = event.getBody();
        System.out.write(body);
        System.out.println();
    }

    /**
     * 配置方法
     *
     * @param context 配置文件
     */
    public void configure(Context context) {

    }
}
