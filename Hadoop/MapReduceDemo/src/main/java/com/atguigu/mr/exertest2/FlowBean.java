package com.atguigu.mr.exertest2;


import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-19 6:22 下午
 */
public class FlowBean implements WritableComparable<FlowBean> {
    private long phone;
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    public FlowBean() {
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }


    @Override
    public String toString() {
        return phone + "\t" + upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

    public int compareTo(FlowBean o) {
        return -(int) (this.sumFlow - o.sumFlow);

    }

    public void write(DataOutput out) throws IOException {
        out.writeLong(phone);
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);

    }

    public void readFields(DataInput in) throws IOException {
        phone = in.readLong();
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
    }
}
