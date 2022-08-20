package com.atguigu.hive.etl;


/**
 * 数据清洗工具类
 * @author Vanas
 * @create 2020-04-29 11:15 上午
 */
public class EtlUtils {

    /**
     * 清洗video数据
     * 规则：
     * 1。判断传入数据是否完整
     * 2。将视频类别的空格去掉
     * 3。将相关视频通过&连接
     * RX24KLBhwMI	lemonette	697	People & Blogs	512	24149	4.22	315	474	t60tW0WevkE	WZgoejVDZlo	Xa_op4MhSkg	MwynZ8qTwXA	sfG2rtAkAcg	j72VLPwzd_c
     */
    public static String etlData(String srcData){
        StringBuilder sbs = new StringBuilder();
//        通过\t
        String[] datas = srcData.split("\t");
        if (datas.length<9){
            return null;
        }
//        2处理视频类别空格问题
        datas[3] = datas[3].replaceAll(" ", "");
//        处理相关视频
        for (int i = 0; i < datas.length; i++) {
            if (i<9){
//                没有相关视频
                if (i<datas.length-1){
                    sbs.append(datas[i]).append("\t");
                }else {
                    sbs.append(datas[i]);
                }
            }else{
//              有相关视频
                if (i<datas.length-1){
                    sbs.append(datas[i]).append("&");

                }else {
                    sbs.append(datas[i]);
                }
            }
        }
        return sbs.toString();
    }

    public static void main(String[] args) {
        String result = etlData("RX24KLBhwMI\tlemonette\t697\tPeople & Blogs\t512\t24149\t4.22\t315\t474\tt60tW0WevkE\tWZgoejVDZlo\tXa_op4MhSkg\tMwynZ8qTwXA\tsfG2rtAkAcg\tj72VLPwzd_c");
        System.out.println(result);
    }

}
