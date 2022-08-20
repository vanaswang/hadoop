package com.atguigu.mr.exertest1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * 第一次处理
 *
 * @author Vanas
 * @create 2020-04-19 4:55 下午
 */
public class OneIndexMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    String name;
    Text outK = new Text();
    IntWritable outV = new IntWritable();

    @Override
    protected void setup(Context context){

//        获取文件名称
        FileSplit split = (FileSplit) context.getInputSplit();
        name = split.getPath().getName();

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] splits = line.split(" ");
        for (String word : splits) {
            outK.set(word + "--" + name);
            outV.set(1);
            context.write(outK, outV);
        }


    }
}
