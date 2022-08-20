package com.atguigu.mr.groupcompartor;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-17 3:22 下午
 */
public class OrderReduce extends Reducer<OrderBean, NullWritable,OrderBean,NullWritable> {


    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
//        保证都是排好序的

//        直接将第一个数据即可
//        context.write(key,NullWritable.get());
        context.write(key,values.iterator().next());
    }
}
