package com.atguigu.test.mapreducetest;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-14 8:55 下午
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    Text outKey = new Text();
    IntWritable outValue = new IntWritable(1);

    /**
     * @param key     输入到第几行 相当于定位
     * @param value   每一行内容
     * @param context 调度mapper运行
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] splits = line.split(" ");
        for (String word : splits) {
            outKey.set(word);
            context.write(outKey,outValue);
        }
    }
}
