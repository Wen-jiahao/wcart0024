package io.wjh.wcartsuppotback.consumer;

import io.wjh.wcartsuppotback.mq.EmailEvent;
import io.wjh.wcartsuppotback.util.EmailUtil;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@RocketMQMessageListener(topic = "SendPwdRestByEmail",consumerGroup = "jcart-support-group01")
public class EmailConsumer implements RocketMQListener<EmailEvent> {

    @Autowired
    private EmailUtil emailUtil;
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public void onMessage(EmailEvent emailEvent) {
        String email = emailEvent.getEmail();
        String fromEmail = emailEvent.getFromEmail();
        String hex = emailEvent.getHex();
        emailUtil.sendEmail(fromEmail,email,hex);
    }
}
