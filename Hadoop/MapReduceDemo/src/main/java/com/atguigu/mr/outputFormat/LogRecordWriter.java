package com.atguigu.mr.outputFormat;

import jdk.nashorn.internal.ir.IfNode;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-17 4:30 下午
 */
public class LogRecordWriter extends RecordWriter<Text, NullWritable> {
    private String baiduPath = "/Users/vanas/Desktop/baidu.log";
    private String otherPath = "/Users/vanas/Desktop/other.log";
    private FSDataOutputStream baiduOut;
    private FSDataOutputStream otherOut;
    private TaskAttemptContext context;

    public LogRecordWriter(TaskAttemptContext context) throws IOException {
        this.context = context;
        //        获取文件系统对象
        FileSystem fs = FileSystem.get(context.getConfiguration());
//        获取流
        baiduOut = fs.create(new Path(baiduPath));
        otherOut = fs.create(new Path(otherPath));
    }

    /**
     * 需求 baidu 可写到 baidu.log
     * 其他写在 other.log
     *
     * @param key
     * @param value
     * @throws IOException
     * @throws InterruptedException
     */

    public void write(Text key, NullWritable value) throws IOException, InterruptedException {

//        判断数据，使用不同的流数据写出
        String log = key.toString();
        if (log.contains("baidu")) {

            baiduOut.writeBytes(log);
        } else {
            otherOut.writeBytes(log + "\n\r");
        }

    }

    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        baiduOut.close();
        otherOut.close();
    }
}
