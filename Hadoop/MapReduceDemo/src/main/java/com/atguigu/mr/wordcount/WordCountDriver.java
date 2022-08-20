package com.atguigu.mr.wordcount;

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
 * @create 2020-04-14 3:23 下午
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//        1创建Job对象
//        获取配置信息以及封装任务
        Configuration conf = new Configuration();
//        设置HDFS NameNode的地址
        conf.set("fs.defaultFS", "hdfs://hadoop130:9820");
        // 指定MapReduce运行在Yarn上
        conf.set("mapreduce.framework.name", "yarn");
        //指定Yarn resourcemanager的位置
        conf.set("yarn.resourcemanager.hostname", "hadoop133");
        // 指定mapreduce可以在远程集群运行
        conf.set("mapreduce.app-submission.cross-platform", "true");
//      指定提交到hive队列
        conf.set("mapred.job.queue.name", "hive");


        Job job = Job.getInstance(conf);
//        2。关联jar
        job.setJar("/Users/vanas/Desktop/BigData/MapReduceDemo/target/MapReduceDemo-1.0-SNAPSHOT.jar");
//        job.setJarByClass(WordCountDriver.class);

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
//        FileInputFormat.setInputPaths(job, new Path("/Users/vanas/Desktop/hello.txt"));
//        FileOutputFormat.setOutputPath(job, new Path("/Users/vanas/Desktop/output"));

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//        7。提交job
        job.waitForCompletion(true);

    }
}
