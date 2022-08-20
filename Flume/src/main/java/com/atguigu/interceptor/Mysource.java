package com.atguigu.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

/**
 * @author Vanas
 * @create 2020-05-05 11:32 上午
 */
public class Mysource extends AbstractSource implements Configurable, PollableSource {
    private String prefix;
    private Long interval;

    /**
     * 拉取事件并交给ChannelProcess处理方法
     *
     * @return
     * @throws EventDeliveryException
     */
    public Status process() throws EventDeliveryException {
        Status status = null;

        try {
            // This try clause includes whatever Channel/Event operations you want to do

            // Receive new data
//            我们通过外部方法拉取数据
            Event e = getSomeData();

            // Store the Event into this Source's associated Channel(s)
            getChannelProcessor().processEvent(e);

            status = Status.READY;
        } catch (Throwable t) {
            // Log exception, handle individual exceptions as needed

            status = Status.BACKOFF;

            // re-throw all Errors
            if (t instanceof Error) {
                throw (Error) t;
            }
        }
        return status;
    }

    /**
     * 拉取数据并包装成Event
     *
     * @return
     */
    private Event getSomeData() throws InterruptedException {
//        通过随机数模拟拉取数据
        int i = (int) (Math.random() * 1000);
//        添加前缀
        String message = prefix + i;

        Thread.sleep(interval);
//       包装成Event
        Event event = new SimpleEvent();
        event.setBody(message.getBytes());
        return event;

    }

    /**
     * 如果拉取不到数据，backoof时间的增长速度
     *
     * @return 增长量
     */
    public long getBackOffSleepIncrement() {
        return 1000;
    }

    /**
     * 最大等待时间
     *
     * @return 时间
     */
    public long getMaxBackOffSleepInterval() {
        return 10000;
    }

    /**
     * 来自于cofigurable 自定义Source
     *
     * @param context 配置文件
     */
    public void configure(Context context) {
        prefix = context.getString("prefff", "XXXX");
        interval = context.getLong("interval", 500L);
    }
}
