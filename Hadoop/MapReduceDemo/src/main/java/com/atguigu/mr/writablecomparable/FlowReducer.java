package com.atguigu.mr.writablecomparable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-17 10:47 上午
 */
public class FlowReducer extends Reducer<FlowBean, Text,Text,FlowBean> {

    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
//    注意不同的手机号但总流量相同会组成一组kv  进入一个reduce方法
//        手机号相同，但总流量不同情况，会被分开处理
//        直接迭代values，将每个手机号的数据直接写出
        for (Text value : values) {
            context.write(value,key);
        }

    }
}
