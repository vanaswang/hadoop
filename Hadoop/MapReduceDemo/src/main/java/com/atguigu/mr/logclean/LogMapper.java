package com.atguigu.mr.logclean;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;


/**
 * @author Vanas
 * @create 2020-04-19 9:52 下午
 */
public class LogMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
//        解析数据
        boolean result = parseLog(line,context);
        if (!result) {
            return;
        }


        context.write(value,NullWritable.get());

    }

    private boolean parseLog(String line, Context context) {
        String[] split = line.split(" ");

        if (split.length>11){
            context.getCounter("map","true").increment(1);
            return true;
        }else
            context.getCounter("map","false").increment(1);

        return false;

    }


}
