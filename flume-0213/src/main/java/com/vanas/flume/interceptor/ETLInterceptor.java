package com.vanas.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Vanas
 * @create 2020-05-12 4:55 下午
 */
public class ETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
//       JSON校验  new String(body, Charset.forName("utf-8"));
        String log = new String(body, StandardCharsets.UTF_8);
        if (JSONUtiles.isJSONValidate(log)) {
            return event;
        }
        return null;
    }


    @Override
    public List<Event> intercept(List<Event> list) {
        //        while (iterator.hasNext()) {
//            Event next = iterator.next();
//            if (intercept(next) == null) {
//                iterator.remove();
//            }
//        }
        list.removeIf(next -> intercept(next) == null);
        return list;
    }

    public static class Builder implements Interceptor.Builder {

        @Override
        public Interceptor build() {
            return new ETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }

    @Override
    public void close() {

    }
}
