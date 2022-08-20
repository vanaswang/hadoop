package com.atguigu.mr.exertest3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * xx是谁的好友
 *
 * @author Vanas
 * @create 2020-04-19 7:43 下午
 */
public class OneShareFriendsMapper extends Mapper<LongWritable, Text, Text, Text> {

    Text outK = new Text();
    Text outV = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] splits = line.split(":");

        String person = splits[0];
        String[] friends = splits[1].split(",");
        outV.set(person);
        for (String friend : friends) {
            outK.set(friend);
            context.write(outK, outV);

        }

    }

}
