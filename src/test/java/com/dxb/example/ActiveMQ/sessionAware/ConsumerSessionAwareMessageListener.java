/*
 * 文件名：ConsumerSessionAwareMessageListener.java 版权：北京明朝万达科技股份有限公司 描述： 修改人：dongxiaobin-0751
 * 修改时间：2016年10月26日
 */

package com.dxb.example.ActiveMQ.sessionAware;


import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;


public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<TextMessage>
{

    private Destination destination;

    public void onMessage(TextMessage message, Session session)
        throws JMSException
    {
        // 接受消息
        System.out.println("SessionAwareMessageListener收到一条消息：\t" + message.getText());
        // 发送消息
        MessageProducer producer = session.createProducer(destination);
        TextMessage tm = session.createTextMessage("I am ConsumerSessionAwareMessageListener");

        producer.send(tm);
    }

    public void setDestination(Destination destination)
    {
        this.destination = destination;
    }
}
