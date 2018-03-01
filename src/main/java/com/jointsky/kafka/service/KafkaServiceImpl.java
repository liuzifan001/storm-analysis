package com.jointsky.kafka.service;

import com.jointsky.util.PropertiesLoader;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

/**
 * Created by LiuZifan on 2018/1/29.
 */
public class KafkaServiceImpl implements KafkaService{
    private Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);
    private KafkaProducer producer = null;
    private static PropertiesLoader loader = new PropertiesLoader("kafka.properties");

    public void establishConnect() throws Exception {
        Properties producerProps = new Properties();
        producerProps.setProperty("bootstrap.servers",loader.getProperty("bootstrap.servers"));
        producerProps.setProperty("compression.codec", loader.getProperty("compression"));
        producerProps.setProperty("queue.buffering.max.ms", loader.getProperty("queue.buffering.max.ms"));
        producerProps.setProperty("queue.enqueue.timeout.ms", loader.getProperty("queue.enqueue.timeout.ms"));
        producerProps.setProperty("request.required.acks", loader.getProperty("request.required.acks"));
        producerProps.setProperty("producer.type", loader.getProperty("producer.type"));
        producerProps.setProperty("key.serializer", loader.getProperty("key.serializer"));
        producerProps.setProperty("value.serializer", loader.getProperty("value.serializer"));
        producerProps.setProperty("partitioner.class", loader.getProperty("partitioner.class"));     //使用自定义分区分配函数
        producer = new KafkaProducer(producerProps);
    }

    public void send(ProducerRecord producerRecord) throws Exception {
        producer.send(producerRecord);
    }

    public void send(List<ProducerRecord> producerRecordList) throws Exception {
        for (ProducerRecord producerRecord : producerRecordList) {
            send(producerRecord);
        }
    }

    public void closeConnect() {
        producer.close();
    }
}
