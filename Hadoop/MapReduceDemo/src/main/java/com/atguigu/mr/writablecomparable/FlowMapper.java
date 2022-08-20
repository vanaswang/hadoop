package com.atguigu.mr.writablecomparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-17 10:47 上午
 */
public class FlowMapper extends Mapper<LongWritable, Text, FlowBean, Text> {
    private FlowBean outK = new FlowBean();
    private Text outV = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        处理一行数据
        String line = value.toString();
        String[] splits = line.split("\t");

//      封装
        outV.set(splits[1]);

        outK.setUpFlow(Long.parseLong(splits[splits.length-3]));
        outK.setDownFlow(Long.parseLong(splits[splits.length-2]));
        outK.setSumFlow();


        context.write(outK,outV);
    }
}
