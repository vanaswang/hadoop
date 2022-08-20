package com.atguigu.test.mapreducetest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-14 9:13 下午
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//        1.创建对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
//        2.关联jar
        job.setJarByClass(WordCountDriver.class);
//        3.关联Mapper和Reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
//        4.设置Mapper输出的key和value的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
//        5.设置最终输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
//        6.设置输入和输出的路径
        FileInputFormat.setInputPaths(job, new Path("/Users/vanas/Desktop/hello.txt"));
        FileOutputFormat.setOutputPath(job, new Path("/Users/vanas/Desktop/output"));
//        7.提交job
        job.waitForCompletion(true);


    }
}
