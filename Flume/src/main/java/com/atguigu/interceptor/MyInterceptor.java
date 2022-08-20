package com.atguigu.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

/**
 * 根据输入数据的首字母不同，添加不同的header来让ChannelSelector处理
 *
 * @author Vanas
 * @create 2020-05-05 9:30 上午
 */

public class MyInterceptor implements Interceptor {
    /**
     * 初始化方法，新建interceptor时候使用
     */
    public void initialize() {


    }

    /**
     * 更改方法 对event进行处理
     *
     * @param event 传入数据
     * @return 处理好的数据
     */
    public Event intercept(Event event) {
//        获取传入事件的Header
        Map<String, String> headers = event.getHeaders();
//        获取body，根据首字母不同来添加header
        byte[] body = event.getBody();
//        获取首字母
        String s = new String(body);
        char c = s.charAt(0);
//        判断首字母是不是字母
//        s.matches("^[a-z,A-Z]")
        if ((c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A')) {
//            是字母
            headers.put("type", "alphabet");
        } else {
//            不是字母
            headers.put("type", "not_alphabet");
        }
//        返回处理好的事件
        return event;

    }

    /**
     * 批处理方法，对传入的一批数据进行处理
     *
     * @param list
     * @return 处理好的数据
     */
    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event);
        }
        return list;
    }

    /**
     * 如果有需要关闭的资源在这个方法中关闭
     */
    public void close() {

    }

    //    框架通过调用Builder来创建Interceptor实例
    public static class MyBuilder implements Interceptor.Builder {
        /**
         * 创建实例的方法
         *
         * @return 新的interceptor
         */
        public Interceptor build() {
            return new MyInterceptor();
        }

        /**
         * 读取配置文件的方法
         *
         * @param context 配置文件
         */
        public void configure(Context context) {

        }
    }
}
