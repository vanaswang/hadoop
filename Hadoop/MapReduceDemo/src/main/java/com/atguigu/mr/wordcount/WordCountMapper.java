package com.atguigu.mr.wordcount;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * 继承Mapper类，指定4个泛型——2组kv对
 * 一组输入数据的kv 另外一组输出的kv
 * <KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 * KEYIN：LongWritable,表示文件中读取数据的偏移量（读到了什么位置，下一次从哪个位置读取）
 * VALUEIN:TEST,表示从文件中读取的一行数据
 * <p>
 * KEYOUT：Test，表示一个单词
 * VALUEOUT：IntWritable ，表示单词出现了一次
 *
 * @author Vanas
 * @create 2020-04-14 3:22 下午
 */


public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    IntWritable outV = new IntWritable(1);
    Text outK = new Text();

    /**
     *
     * @param key 输入的key（keyin 位置）
     * @param value 输入的v，文件中读取的一行内容
     * @param context 负责调度Mapper运行
     * @throws IOException
     * @throws InterruptedException
     */

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//      1。 一行数据转换成String
        String line = value.toString();
//      2.使用空格切分数据
        String[] splits = line.split(" ");
//        3.迭代splits数组 ，将每个单词处理为kv写出去
        for (String word : splits) {
            outK.set(word);
            context.write(outK, outV);
        }
    }
}
