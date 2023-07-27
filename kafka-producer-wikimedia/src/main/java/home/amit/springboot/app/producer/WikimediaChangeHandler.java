package home.amit.springboot.app.producer;
/*
User :- AmitSingh
Date :- 7/26/2023
Time :- 6:43 PM
Year :- 2023
*/

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class WikimediaChangeHandler implements EventHandler {

private KafkaTemplate<String,String> kafkaTemplate;
private String topicName;

public WikimediaChangeHandler(KafkaTemplate<String,String> kafkaTemplate, String topicName)
{

    this.kafkaTemplate=kafkaTemplate;
    this.topicName=topicName;
}


    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {

    log.info(String.format("Event Data -> %s ",messageEvent.getData().substring(50,100)) );
    kafkaTemplate.send(topicName,messageEvent.getData().substring(0,10));
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
