package com.atguigu.mr.inputformat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-15 4:36 下午
 */
public class KVMapper extends Mapper<Text, Text, Text, IntWritable> {
    IntWritable outV = new IntWritable(1);

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
//        读到的一行数据会按照预先设置的分割符进行切分，左边作为key 右边为Value 进入到mapper中
        context.write(key, outV);

    }
}
