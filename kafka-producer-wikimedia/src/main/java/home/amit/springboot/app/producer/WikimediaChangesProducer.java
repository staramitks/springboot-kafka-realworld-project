package home.amit.springboot.app.producer;
/*
User :- AmitSingh
Date :- 7/26/2023
Time :- 6:30 PM
Year :- 2023
*/

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WikimediaChangesProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${spring.kafka.topicName}")
    private String topicName;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate)
    {
        this.kafkaTemplate=kafkaTemplate;
    }


    public void sendMessage() throws InterruptedException {
        // To read realtime stream data form wikimedia use event source
        EventHandler eventHandler= new WikimediaChangeHandler(kafkaTemplate,topicName);
        String url="https://stream.wikimedia.org/v2/stream/recentchange/";
        EventSource.Builder eventSourceBuilder=new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource= eventSourceBuilder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);

    }


}
