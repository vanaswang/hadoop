package com.atguigu.hive;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

/**
 * 计算传入的字符串的长度并返回
 *
 * @author Vanas
 * @create 2020-04-28 2:15 下午
 */
public class MyUDF extends GenericUDF {
    /**
     * 对输入参数的判断处理和返回值类型的一个约定
     *
     * @param arguments 传入到函数的参数类型
     * @return
     * @throws UDFArgumentException
     */
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
//        判断输入参数的个数 和空值
        if (arguments == null || arguments.length != 1) {
            throw new UDFArgumentLengthException("input args length error!!");
        }
        // 判断输入参数的类型
        if (!arguments[0].getCategory().equals(ObjectInspector.Category.PRIMITIVE)) {
            throw new UDFArgumentTypeException(0, "input args type error!!!");
        }
        //约定函数的返回值类型为int
        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
    }

    /**
     * 函数的逻辑处理
     *
     * @param arguments 传入到函数的参数
     * @throws HiveException
     */
    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        //取出参数
        Object o = arguments[0].get();
        if (o == null) {
            return 0;
        }
        return o.toString().length();
    }

    public String getDisplayString(String[] strings) {

        return "";
    }
}
