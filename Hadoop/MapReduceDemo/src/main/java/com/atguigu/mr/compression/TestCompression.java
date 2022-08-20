package com.atguigu.mr.compression;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Vanas
 * @create 2020-04-20 2:44 下午
 */
public class TestCompression {
    //    数据流压缩：使用支持压缩的输出流将字节数据写出就实现压缩
    @Test
    public void testCompression90() throws IOException, ClassNotFoundException {
        String srcFile = "/Users/vanas/Desktop/input/hello.txt";
        String destFile = "/Users/vanas/Desktop/ot";
        //        获取文件系统对象
        FileSystem fs = FileSystem.get(new Configuration());
        Configuration conf = new Configuration();

//      输入流
        FSDataInputStream in = fs.open(new Path(srcFile));
//        输出流
//        使用的编解码器
        String codesClassName = "org.apache.hadoop.io.compress.DefaultCodec";
        Class<?> codeClass = Class.forName(codesClassName);
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeClass, conf);
//      获取当切编解码器对应文件的扩展名
        FSDataOutputStream out = fs.create(new Path(destFile + codec.getDefaultExtension()));
//        包装输出流
        CompressionOutputStream codeOut = codec.createOutputStream(out);
//       流的拷贝
        IOUtils.copyBytes(in, codeOut, conf);
//       关闭
        IOUtils.closeStreams(in, codeOut);

    }


    //    数据流的解压缩：使用支持压缩的输入流将字节数据读取就实现解压缩
    @Test
    public void testDeCompression() throws IOException {
//         待解压文件
        String srcFile = "/Users/vanas/Desktop/output.deflate";
        String destFile = "/Users/vanas/Desktop/output.txt";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

//        获取编解码器,输入文件的扩展名获取编解码器
        CompressionCodec codec = new CompressionCodecFactory(conf).getCodec(new Path(srcFile));

//        输入流
        FSDataInputStream in = fs.open(new Path(srcFile));
        CompressionInputStream codecIn = codec.createInputStream(in);
//        输出流
        FSDataOutputStream out = fs.create(new Path(destFile));
//        流的拷贝
        IOUtils.copyBytes(codecIn, out, conf);
//        关闭
        IOUtils.closeStreams(codecIn, out);

    }

}
