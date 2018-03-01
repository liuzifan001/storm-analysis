package com.jointsky.kafka.service;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;

/**
 *          1. 创建对象EdpsKafkaService edpsKafkaService = new EdpsKafkaServiceImpl();
 *          2. 建立连接 edpsKafkaService.establishConnect();
 *          3. 发送数据 数据是由消息封装体来构成,包括要发送的消息和消息主题 topic
 *              提供了发送单条和批量多条接口 send(MessageData messageData) 和 send(List<MessageData> messageDataList)
 *          4. 连接关闭,释放资源 edpsKafkaService.closeConnect()
 */
public interface KafkaService {
    /**
     * 建立连接,设置参数,创建Producer实例
     * @param
     */
    void establishConnect() throws  Exception;

    /**
     * 发送一条消息
     * @param producerRecord 消息数据
     */
    void send(ProducerRecord producerRecord) throws  Exception;

    /**
     * 批量发送接口，一次发送多条消息
     * @param producerRecordList  存放多条消息到list中
     */
    void send(List<ProducerRecord> producerRecordList) throws Exception;

    /**
     * 连接关闭,释放资源
     */
    void closeConnect();

}
