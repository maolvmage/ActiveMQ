/*
 * 文件名：SenderImpl.java 版权：北京明朝万达科技股份有限公司 描述： 修改人：dongxiaobin-0751 修改时间：2016年10月26日
 */

package com.dxb.example.ActiveMQ.queue;


import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;


public class SenderImpl
{
    private JmsTemplate jmsTemplate;

    /**
     * 向指定队列发送消息
     */
    public void sendMessage(Destination destination, final String msg)
    {
        System.out.println("向队列" + destination.toString() + "发送了消息" + msg);
        jmsTemplate.send(destination, new MessageCreator()
        {
            public Message createMessage(Session session)
                throws JMSException
            {
                return session.createTextMessage(msg);
            }
        });
    }

    /**
     * 向默认队列发送消息
     */
    public void sendMessage(final String msg)
    {
        String destination = jmsTemplate.getDefaultDestination().toString();
        jmsTemplate.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        jmsTemplate.setDeliveryPersistent(false);
        System.out.println("向队列" + destination + "发送了消息:" + msg);
        jmsTemplate.send(new MessageCreator()
        {
            public Message createMessage(Session session)
                throws JMSException
            {
                return session.createTextMessage(msg);
            }
        });

    }

    public void sendMessage(Destination destination, final String msg, final Destination response)
    {
        System.out.println("ProducerService向队列" + destination + "发送了消息：\t" + msg);
        jmsTemplate.send(destination, new MessageCreator()
        {
            public Message createMessage(Session session)
                throws JMSException
            {
                TextMessage textMessage = session.createTextMessage(msg);
                textMessage.setJMSReplyTo(response);
                return textMessage;
            }
        });
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }

}