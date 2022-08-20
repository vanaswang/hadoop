package com.vanas.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Vanas
 * @create 2020-05-08 10:46 上午
 */
public class MyProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        1.new 对象，初始化一个kafkaProducer的抽象对象
        Properties properties = new Properties();
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("ack", "all");
        properties.setProperty("bootstrap.servers", "hadoop130:9092");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
//        2。做事情
        for (int i = 0; i < 10; i++) {

            Future<RecordMetadata> result = producer.send(new ProducerRecord<String, String>("first", "Messige" + i, " this is:" + i)
                    , new Callback() {
                        //                  回调函数 当收到brokerack时做的事情
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            if (metadata != null) {
                                String topic = metadata.topic();
                                int partition = metadata.partition();
                                long offset = metadata.offset();
                                System.out.println("消息发送成功:" + topic + "\t" + partition + "\t" + offset);
                            }
                        }
                    });
//            RecordMetadata recordMetadata = result.get(); //同步发送 会阻塞
            System.out.println("第" + i + "条发送结束");

        }
//        3。关闭资源
        producer.close();
    }
}
