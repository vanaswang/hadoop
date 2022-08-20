package com.atguigu.mr.exertest2;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-19 6:22 下午
 */
public class TopNReducer extends Reducer<FlowBean, NullWritable, FlowBean, NullWritable> {
    int i = 0;

    @Override
    protected void reduce(FlowBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        while (i < 10) {

            context.write(key, NullWritable.get());
            i++;
            break;
        }
    }
}
