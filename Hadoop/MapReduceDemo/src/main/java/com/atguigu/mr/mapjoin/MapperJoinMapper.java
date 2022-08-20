package com.atguigu.mr.mapjoin;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import java.util.HashMap;
import java.util.Map;

/**
 * 小表文件加载到内存中，接下来每读取一条大表数据，就与内存中的小表的数据进行join，join完后直接写出
 *
 * @author Vanas
 * @create 2020-04-18 3:41 下午
 */
public class MapperJoinMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    private Map<String, String> pdMap = new HashMap<String, String>();
    private Text outK = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        context.getCounter("Map Join","setup").increment(1);
//        小表数据加载内存中
//      获取在driver中设置的缓存文件
        URI[] cacheFiles = context.getCacheFiles();
        URI currentCacheFile = cacheFiles[0];
//        读取文件
        FileSystem fs = FileSystem.get(context.getConfiguration());
//        获取输入流
        FSDataInputStream in = fs.open(new Path(currentCacheFile.getPath()));
//          一次读取文件中一行数据
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
//            切割
            String[] split = line.split("\t");
            pdMap.put(split[0], split[1]);
        }


    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        context.getCounter("Map Join","map").increment(1);
        //       读大数据

        String line = value.toString();
        String[] splits = line.split("\t");
        String currentPname = pdMap.get(splits[1]);
        String resultLine = splits[0] + "\t" + currentPname + "\t" + splits[2];
        outK.set(resultLine);

        context.write(outK, NullWritable.get());

    }
}
