package com.atguigu.test.exam;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-23 10:11 下午
 */
public class OrderReducer extends Reducer<Text, OrderBean, OrderBean, NullWritable> {


    @Override
    protected void reduce(Text key, Iterable<OrderBean> values, Context context) throws IOException, InterruptedException {
        for (OrderBean value : values) {
            context.write(value, NullWritable.get());
        }
    }
}
