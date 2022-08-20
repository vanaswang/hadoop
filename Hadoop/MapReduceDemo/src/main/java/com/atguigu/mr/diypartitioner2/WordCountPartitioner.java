package com.atguigu.mr.diypartitioner2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author Vanas
 * @create 2020-04-17 1:09 下午
 */
public class WordCountPartitioner extends Partitioner<Text, IntWritable> {


    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {
        int partitioner;
        String key = text.toString();

        if (key.charAt(0) > 'a' && key.charAt(0) < 'p') {
            partitioner = 0;
        } else {
            partitioner = 1;
        }
        return partitioner;
    }
}
