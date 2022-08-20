package com.atguigu.mr.reducejoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vanas
 * @create 2020-04-18 2:42 下午
 */
public class ReduceJoinReducer extends Reducer<Text, OrderBean, OrderBean, NullWritable> {
//  定义存储Order数据的OrderBean集合
    List<OrderBean> orders = new ArrayList<OrderBean>();
//  定义OrderBean，粗处pd的数据
    OrderBean pdBean = new OrderBean();


    @Override
    protected void reduce(Text key, Iterable<OrderBean> values, Context context) throws IOException, InterruptedException {
//        pid相同会进入一个方法
//        思路；将所有order的数据全部获取到，保存到一个集合中，把pd的数据获取到，保存在对象中
//        迭代保存order数据的集合，获取到每个order数据的orderBean对象，把pd对象中的pname设置到每个order数据的orderbean对象中
        for (OrderBean orderBean : values) {
            if ("order".equals(orderBean.getFlag())) {
//                深拷贝
                OrderBean currentOrderbean = new OrderBean();
                try {
                    BeanUtils.copyProperties(currentOrderbean, orderBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                orders.add(currentOrderbean);
            } else {
//                pd
                try {
                    BeanUtils.copyProperties(pdBean, orderBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
        for (OrderBean orderBean : orders) {
            orderBean.setPname(pdBean.getPname());
            context.write(orderBean, NullWritable.get());
        }
//        清空集合
        orders.clear();

    }
}
