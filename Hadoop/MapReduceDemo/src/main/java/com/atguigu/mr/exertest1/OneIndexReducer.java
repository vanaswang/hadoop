package com.atguigu.mr.exertest1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-19 4:55 下午
 */
public class OneIndexReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    IntWritable outV = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();

        }
        outV.set(sum);

        context.write(key,outV);
    }
}
