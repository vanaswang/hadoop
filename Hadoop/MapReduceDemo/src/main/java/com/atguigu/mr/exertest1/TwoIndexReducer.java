package com.atguigu.mr.exertest1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-19 5:47 下午
 */
public class TwoIndexReducer extends Reducer<Text, Text, Text, Text> {


    Text outV = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String str = "";
        for (Text value : values) {
            str += value;
        }
        outV.set(str);

        context.write(key, outV);
    }
}
