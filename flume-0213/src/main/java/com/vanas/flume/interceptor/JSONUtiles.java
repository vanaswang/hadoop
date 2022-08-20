package com.vanas.flume.interceptor;

        import com.alibaba.fastjson.JSON;
        import com.alibaba.fastjson.JSONException;


/**
 * @author Vanas
 * @create 2020-05-13 8:48 上午
 */
public class JSONUtiles {
    public static boolean isJSONValidate(String log) {
        try {
            JSON.parse(log);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}
