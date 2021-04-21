package com.chocolateminds.sns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsync;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class SNSMessagePublisher implements CommandLineRunner {

    private NotificationMessagingTemplate notificationMessageTemplate;

    private final String TOPIC_NAME = "arn:aws:sns:eu-west-2:304593876351:bootcloudaws-topic";

    @Autowired
    public SNSMessagePublisher(AmazonSNS amazonSNS){
        notificationMessageTemplate = new NotificationMessagingTemplate(amazonSNS);
    }

    private void sendMessage(String topicName){
        notificationMessageTemplate.sendNotification(topicName,
                "Here's the test message",
                "Hello AWS Gurus!");
    }

    @Override
    public void run(String... args) throws Exception {
        sendMessage(TOPIC_NAME);
    }
}
