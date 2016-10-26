/*
 * 文件名：TopicListener.java 版权：北京明朝万达科技股份有限公司 描述： 修改人：dongxiaobin-0751 修改时间：2016年10月26日
 */

package com.dxb.example.ActiveMQ.topic;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class TopicListener implements MessageListener
{
    public void onMessage(Message message)
    {
        TextMessage tm = (TextMessage)message;
        try
        {
            System.out.println("TopicListener \t" + tm.getText());
        }
        catch (JMSException e)
        {
            e.printStackTrace();
        }
    }
}
