package com.atguigu.mr.combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**还是继承Reducer 但是是在MapTask里运行
 * @author Vanas
 * @create 2020-04-17 11:47 上午
 */
public class WordCountCombiner extends Reducer<Text, IntWritable, Text,IntWritable> {
    private IntWritable outV = new IntWritable();


    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum +=value.get();
        }
        outV.set(sum);
        context.write(key,outV);
    }
}
