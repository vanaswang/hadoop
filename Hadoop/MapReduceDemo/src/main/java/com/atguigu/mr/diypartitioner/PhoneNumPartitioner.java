package com.atguigu.mr.diypartitioner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author Vanas
 * @create 2020-04-17 10:18 上午
 */
public class PhoneNumPartitioner extends Partitioner<Text,FlowBean> {
    /**
     * 需求：136、137、138、139 其他
     * @param text
     * @param flowBean
     * @param numPartitions
     * @return
     */
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        int partitioner;
        String key = text.toString();
        if (key.startsWith("136")){
            partitioner = 0;
        }else if (key.startsWith("137")){
            partitioner = 1;
        }else if (key.startsWith("138")){
            partitioner = 2;
        }else if (key.startsWith("139")){
            partitioner = 3;
        }else{
            partitioner = 4;
        }
        return partitioner;
    }
}
