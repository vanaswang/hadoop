package com.atguigu.mr.groupcompartor;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-17 3:22 下午
 */
public class OrderBean implements WritableComparable<OrderBean> {
    private String orderId;
    private Double price;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderBean() {
    }

    @Override
    public String toString() {
        return orderId + "\t" + price;
    }

    public int compareTo(OrderBean o) {

        return this.orderId.compareTo(o.getOrderId()) == 0 ? -this.price.compareTo(o.getPrice()) : this.orderId.compareTo(o.getOrderId());

    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(orderId);
        out.writeDouble(price);
    }

    public void readFields(DataInput in) throws IOException {
        orderId = in.readUTF();
        price = in.readDouble();
    }
}
