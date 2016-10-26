/*
 * 文件名：TestMQ.java 版权：北京明朝万达科技股份有限公司 描述： 修改人：dongxiaobin-0751 修改时间：2016年10月26日
 */

package src.test.junit;


import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dxb.example.ActiveMQ.queue.Receiver;
import com.dxb.example.ActiveMQ.queue.SenderImpl;
import com.dxb.example.ActiveMQ.topic.TopicSender;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/resource/spring.xml")
public class TestMQ
{
    @Autowired
    private Destination queueDestination;

    @Autowired
    private Destination queueDestination2;

    @Autowired
    @Qualifier("senderImpl")
    private SenderImpl senderImpl;

    @Autowired
    @Qualifier("receiver")
    private Receiver receiver;

    @Autowired
    @Qualifier("topicDestination")
    private Destination topic;

    @Autowired
    @Qualifier("topicSender")
    private TopicSender topicSender;

    /**
     * Description: 测试队列发送者<br>
     */
    @Test
    public void sendQueueTest()
    {
        senderImpl.sendMessage("sss");
    }

    /**
     * Description: 测试队列消费者<br>
     */
    @Test
    public void testConsume()
    {
        receiver.receive(queueDestination);
    }

    /**
     * Description: 测试监听容器<br>
     */
    @Test
    public void testListenerSend()
    {
        senderImpl.sendMessage(queueDestination2, "Hello China~~~~~~~~~~~~~~~");
    }

    /**
     * Description: 测试主题发送<br>
     */
    @Test
    public void testTopicSender()
    {
        topicSender.publish(topic, "haha");
    }

}
