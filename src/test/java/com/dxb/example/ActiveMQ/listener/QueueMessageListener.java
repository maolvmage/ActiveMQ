/*
 * 文件名：QueueMessageListener.java 版权：北京明朝万达科技股份有限公司 描述： 修改人：dongxiaobin-0751 修改时间：2016年10月26日
 */

package com.dxb.example.ActiveMQ.listener;


import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import com.dxb.example.ActiveMQ.bean.User;


public class QueueMessageListener implements MessageListener
{
    public void onMessage(Message message)
    {
        TextMessage msg = (TextMessage)message;
        try
        {
            System.out.println("当前消息的类型为：" + message.getJMSType());
        }
        catch (JMSException e)
        {}
        // 如果是文本消息
        if (message instanceof TextMessage)
        {
            TextMessage tm = (TextMessage)message;
            try
            {
                System.out.println("ConsumerService从队列" + "收到了消息：\t" + tm.getText());
            }
            catch (JMSException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // 如果是Map消息
        if (message instanceof MapMessage)
        {
            MapMessage mm = (MapMessage)message;
            try
            {
                System.out.println("ConsumerService从队列" + "收到了消息：\t" + mm.getString("name"));
            }
            catch (JMSException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // 如果是Object消息
        if (message instanceof ObjectMessage)
        {
            ObjectMessage om = (ObjectMessage)message;
            User user = null;
            try
            {
                user = (User)om.getObject();
            }
            catch (JMSException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("ConsumerService从队列" + "收到了消息：\t" + user);
        }

        // 如果是bytes消息
        if (message instanceof BytesMessage)
        {
            byte[] b = new byte[1024];
            int len = -1;
            BytesMessage bm = (BytesMessage)message;
            try
            {
                while ((len = bm.readBytes(b)) != -1)
                {
                    System.out.println(new String(b, 0, len));
                }
            }
            catch (JMSException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // 如果是Stream消息
        if (message instanceof StreamMessage)
        {
            StreamMessage sm = (StreamMessage)message;
            try
            {
                System.out.println(sm.readString());
                System.out.println(sm.readInt());
            }
            catch (JMSException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
