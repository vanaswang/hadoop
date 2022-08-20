package com.atguigu.mr.mapjoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Vanas
 * @create 2020-04-18 3:41 下午
 */
public class MapperJoinDriver {
    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

//      设置缓存文件  可以设置多个
        job.addCacheFile(new URI("file:///Users/vanas/Desktop/input/join/pd.txt"));

        job.setJarByClass(MapperJoinDriver.class);
        job.setMapperClass(MapperJoinMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

//        设置ReduceTask的个数为0
        job.setNumReduceTasks(0);
        FileInputFormat.setInputPaths(job,new Path("/Users/vanas/Desktop/input/join/order.txt"));
        FileOutputFormat.setOutputPath(job,new Path("/Users/vanas/Desktop/output"));
        job.waitForCompletion(true);


    }
}
