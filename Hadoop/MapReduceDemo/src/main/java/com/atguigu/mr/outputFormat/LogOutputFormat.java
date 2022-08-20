package com.atguigu.mr.outputFormat;

        import org.apache.hadoop.io.NullWritable;
        import org.apache.hadoop.io.Text;
        import org.apache.hadoop.mapreduce.RecordWriter;
        import org.apache.hadoop.mapreduce.TaskAttemptContext;
        import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

        import java.io.IOException;

/**
 *
 *
 * @author Vanas
 * @create 2020-04-17 4:23 下午
 */
public class LogOutputFormat extends FileOutputFormat<Text, NullWritable> {


    public RecordWriter getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
        return new LogRecordWriter(job);
    }
}


