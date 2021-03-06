package com.atguigu.mr.diypartitioner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-15 10:30 上午
 */
public class FlowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//        1.创建job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

//        2。关联jar
        job.setJarByClass(FlowDriver.class);

//        3。关联Mapper reducer
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);
//        4。设置map输出的 k v
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
//        5。设置最终输出的 k v
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
//        6设置输入和输出路径
        FileInputFormat.setInputPaths(job, new Path("/Users/vanas/Desktop/phone_data.txt"));
        FileOutputFormat.setOutputPath(job, new Path("/Users/vanas/Desktop/output"));
//        设置分区器
        job.setPartitionerClass(PhoneNumPartitioner.class);
//        设置ReduceTask的个数 为PhoneNumParititioner类中逻辑决定分区数
        job.setNumReduceTasks(5);
//        提交job
        job.waitForCompletion(true);
    }
}
