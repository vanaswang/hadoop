package com.atguigu.mr.exertest3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-19 7:44 下午
 */
public class OneShareFriendsReducer extends Reducer<Text,Text,Text,Text> {
    Text outV = new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        StringBuffer str = new StringBuffer();

        for (Text value : values) {
            str.append(value).append(",");
        }
        outV.set(str.toString());
        context.write(key,outV);

    }
}
