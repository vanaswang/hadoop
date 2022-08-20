package com.atguigu.test.exam;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 分别有id，name，mark，source四个字段，按照mark分组，id排序，手写一个MapReduce？
 *
 * @author Vanas
 * @create 2020-04-23 9:51 下午
 */
public class OrderBean implements WritableComparable<OrderBean> {
    private Integer id;
    private String name;
    private String mark;
    private String source;

    public OrderBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public void write(DataOutput out) throws IOException {
        out.writeInt(id);
        out.writeUTF(name);
        out.writeUTF(mark);
        out.writeUTF(source);

    }

    public void readFields(DataInput in) throws IOException {
        id = in.readInt();
        name = in.readUTF();
        mark = in.readUTF();
        source = in.readUTF();

    }

    public int compareTo(OrderBean o) {
        return this.id - o.id;
    }

    @Override
    public String toString() {
        return id + "\t'" + name + "\t" + mark + "\t" + source ;
    }
}
