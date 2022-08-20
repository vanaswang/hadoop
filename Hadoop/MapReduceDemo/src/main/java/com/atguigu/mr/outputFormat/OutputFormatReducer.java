package com.atguigu.mr.outputFormat;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-17 4:24 下午
 */
public class OutputFormatReducer extends Reducer<Text, NullWritable, Text, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
//   迭代values直接写出
        for (NullWritable value : values) {
            context.write(key, value);
        }
    }
}
