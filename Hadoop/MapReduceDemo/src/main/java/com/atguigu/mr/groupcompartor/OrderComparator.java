package com.atguigu.mr.groupcompartor;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author Vanas
 * @create 2020-04-17 3:33 下午
 */
public class OrderComparator extends WritableComparator {

    public OrderComparator() {
        super(OrderBean.class, true);

    }

    /**
     * 比较规则：比较id
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean aBean = (OrderBean) a;
        OrderBean bBean = (OrderBean) b;

        return aBean.getOrderId().compareTo(bBean.getOrderId());

    }
}
