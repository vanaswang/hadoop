package com.atguigu.mr.combiner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-14 3:23 下午
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//        1创建Job对象
//        获取配置信息以及封装任务
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
//        2。关联jar
        job.setJarByClass(WordCountDriver.class);

//        3。关联Mapper 和 Reduce类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
//        4。设置Mapper的输出key和value的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
//        5设置最终输出key和value的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


//        6。设置输入和输出路径
        FileInputFormat.setInputPaths(job, new Path("/Users/vanas/Desktop/hello.txt"));
        FileOutputFormat.setOutputPath(job, new Path("/Users/vanas/Desktop/output4"));
        job.setCombinerClass(WordCountCombiner.class);

//        7。提交job
        job.waitForCompletion(true);

    }
}
