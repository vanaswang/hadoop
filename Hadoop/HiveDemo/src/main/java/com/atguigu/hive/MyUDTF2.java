package com.atguigu.hive;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;

/**
 *
 * 将传入的字符串，按照分隔符拆分，输出多行多列
 * "hello_word，hadoop_hive，java_php"
 * 结果 hello   word
 *     hadoop   hive
 *     java    php
 *
 *
 * @author Vanas
 * @create 2020-04-28 3:04 下午
 */
public class MyUDTF2 extends GenericUDTF {
    private ArrayList<String> outList = new ArrayList<String>();

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
        ArrayList<String> fieldNames = new ArrayList<String>();
//        约定函数输出列的类型
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();

        fieldNames.add("word1");
        fieldNames.add("word2");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    /**
     * 函数的处理逻辑
     *  函数调用：my_explode("hello_word，hadoop_hive，java_php",",","_")
     * @param args
     * @throws HiveException
     */
    public void process(Object[] args) throws HiveException {
//        简单判断
        if (args == null || args.length < 3) {
            throw new HiveException("input args length error !");
        }
//        获取待处理的数据
        String argsData = args[0].toString();
//        获取分隔符1
        String argsSplit1 = args[1].toString();
//        获取分隔符2
        String argsSplit2 = args[2].toString();
//         切割数据
        String[] words = argsData.split(argsSplit1);
//        迭代写出

        for (String word : words) {
//
            outList.clear();
//            继续切割
            String[] currentWords = word.split(argsSplit2);
            for (String currentWord : currentWords) {

                outList.add(currentWord);
            }
            //            写出
            forward(outList);
        }
    }

    public void close() throws HiveException {

    }

}
