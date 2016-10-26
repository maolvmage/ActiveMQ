/*
 * 文件名：Receiver.java 版权：北京明朝万达科技股份有限公司 描述： 修改人：dongxiaobin-0751 修改时间：2016年10月26日
 */

package com.dxb.example.ActiveMQ.queue;


import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;


public class Receiver
{

    private JmsTemplate jmsTemplate;

    /**
     * 接受消息
     */
    public void receive(Destination destination)
    {
        TextMessage tm = (TextMessage)jmsTemplate.receive(destination);
        try
        {
            System.out.println("从队列" + destination.toString() + "收到了消息：\t" + tm.getText());
        }
        catch (JMSException e)
        {
            e.printStackTrace();
        }
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }

}