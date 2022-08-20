package com.atguigu.mr.diypartitioner2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * <KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 * KEYIN: Text，Mapper输出的key ，单词
 * VALUEIN:IntWritable，Mapper输出的v，单词出现的次数
 * <p>
 * KEYOUT:Text，单词
 * VALUEOUT: IntWritable，单词出现的总次数
 *
 * @author Vanas
 * @create 2020-04-14 3:23 下午
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    IntWritable outV = new IntWritable();

    /**
     * @param key     输入的key，一个单词
     * @param values  表示封装了当前key对应所有的value的一个迭代器对象
     * @param context 负责调度Reducer运行
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
//        1。迭代values将当前key对应的所有value累加
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
//        2.写出 将累加的结果sum封装到outV中
        outV.set(sum);

        context.write(key, outV);
    }
}
