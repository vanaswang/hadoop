package com.atguigu.hive;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 将指定的字符串通过指定的分隔符，进行拆分返回多行数据
 * 自定义udtf继承genericUDTF
 *
 * @author Vanas
 * @create 2020-04-28 2:41 下午
 */
public class MyUDTF extends GenericUDTF {


    private List<String> outList = new ArrayList<String>();

    /**
     * 约定函数输出的列的名字和 列的类型
     *
     * @param argOIs
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
//        约定函数输出的列的名字
        List<String> fieldNames = new ArrayList<String>();
//        约定函数输出列的类型
        List<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();

        fieldNames.add("word");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);


        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    /**
     * 函数的处理逻辑
     *
     * @param args
     * @throws HiveException
     */
    public void process(Object[] args) throws HiveException {
//        简单判断
        if (args == null || args.length < 2) {
            throw new HiveException("input args length error !");
        }
//        获取待处理的数据
        String argsData = args[0].toString();
//        获取分隔符
        String argsSplit = args[1].toString();
//         切割数据
        String[] words = argsData.split(argsSplit);
//        迭代写出

        for (String word : words) {
            //集合为复用的，首先清空集合
            outList.clear();
            //            写出
            outList.add(word);
            forward(outList);
        }
    }

    public void close() throws HiveException {

    }
}
