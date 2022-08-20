package com.atguigu.test.exam;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-23 9:58 下午
 */
public class OrderMapper extends Mapper<LongWritable, Text, Text, OrderBean> {
    Text outK = new Text();
    OrderBean outV = new OrderBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] split = line.split(" ");

        outK.set(split[3]);
        outV.setId(Integer.parseInt(split[0]));
        outV.setName(split[1]);
        outV.setMark(split[2]);
        outV.setSource(split[3]);

        context.write(outK, outV);

    }
}
