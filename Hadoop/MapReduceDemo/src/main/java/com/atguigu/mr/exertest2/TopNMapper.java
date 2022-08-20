package com.atguigu.mr.exertest2;

        import org.apache.hadoop.io.LongWritable;
        import org.apache.hadoop.io.NullWritable;
        import org.apache.hadoop.io.Text;
        import org.apache.hadoop.mapreduce.Mapper;

        import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-19 6:22 下午
 */
public class TopNMapper extends Mapper<LongWritable, Text, FlowBean, NullWritable> {
    FlowBean outK = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] split = line.split("\t");
        outK.setPhone(Long.parseLong(split[0]));
        outK.setUpFlow(Long.parseLong(split[1]));
        outK.setDownFlow(Long.parseLong(split[2]));
        outK.setSumFlow(Long.parseLong(split[3]));

        context.write(outK, NullWritable.get());
    }
}
