package com.atguigu.mr.reducejoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-18 2:42 下午
 */
public class ReducerJoinDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
//        2。关联jar
        job.setJarByClass(ReducerJoinDriver.class);

//        3。关联Mapper 和 Reduce类
        job.setMapperClass(ReduceJoinMapper.class);
        job.setReducerClass(ReduceJoinReducer.class);
//        4。设置Mapper的输出key和value的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(OrderBean.class);
//        5设置最终输出key和value的类型
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);
//        6。设置输入和输出路径
        FileInputFormat.setInputPaths(job, new Path("/Users/vanas/Desktop/input/join"));
        FileOutputFormat.setOutputPath(job, new Path("/Users/vanas/Desktop/output1"));

//        7。提交job
        job.waitForCompletion(true);
    }
}
