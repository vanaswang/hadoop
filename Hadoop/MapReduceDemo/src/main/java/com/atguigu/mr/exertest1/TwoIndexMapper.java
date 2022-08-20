package com.atguigu.mr.exertest1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-19 5:47 下午
 */
public class TwoIndexMapper extends Mapper<LongWritable, Text, Text, Text> {
    Text OutK = new Text();
    Text OutV = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] splits = line.split("--");
        OutK.set(splits[0]);

        String[] split = splits[1].split("\t");
        OutV.set(split[0] + "-->" + split[1] + " ");

        context.write(OutK, OutV);


    }
}
