package com.atguigu.mr.reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-18 2:42 下午
 */
public class ReduceJoinMapper extends Mapper<LongWritable, Text, Text, OrderBean> {

    private String currentSplitFileName;
    private OrderBean OutV = new OrderBean();
    private Text OutK = new Text();

    /**
     * 在MapTask开始时执行一次
     * 获取当前处理切片对应的文件是哪个
     *
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
//        获取当前切片对象
        InputSplit inputSplit = context.getInputSplit();
//        转换成FileSplit
        FileSplit currentSplit = (FileSplit) inputSplit;
//        获取当前处理的文件名
        currentSplitFileName = currentSplit.getPath().getName();

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        处理一行数据
        String line = value.toString();
        String[] splits = line.split("\t");


        if (currentSplitFileName.contains("order")) {
//            数据来源与order.txt
//            封装key
            OutK.set(splits[1]);
//             封装V
            OutV.setOrderId(splits[0]);
            OutV.setPid(splits[1]);
            OutV.setAmount(Integer.parseInt(splits[2]));
            OutV.setPname("");
            OutV.setFlag("order");
        } else {

//            数据来源pd.txt
//            封装K
            OutK.set(splits[0]);
//            封装v
            OutV.setPid(splits[0]);
            OutV.setPname(splits[1]);
            OutV.setOrderId("");
            OutV.setAmount(0);
            OutV.setFlag("pd");
        }


        context.write(OutK, OutV);


    }
}
