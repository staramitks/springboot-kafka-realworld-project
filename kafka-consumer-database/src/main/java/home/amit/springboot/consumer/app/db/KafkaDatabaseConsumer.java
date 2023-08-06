package home.amit.springboot.consumer.app.db;
/*
User :- AmitSingh
Date :- 7/27/2023
Time :- 5:37 PM
Year :- 2023
*/

import home.amit.springboot.consumer.app.entity.WikiMediaData;
import home.amit.springboot.consumer.app.repositoty.WikiMediaDataRepositoty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaDatabaseConsumer {

    WikiMediaDataRepositoty wikiMediaDataRepository;

    public KafkaDatabaseConsumer(WikiMediaDataRepositoty wikiMediaDataRepository)
    {
        this.wikiMediaDataRepository =wikiMediaDataRepository;
    }

    @KafkaListener(topics="wikimedia_topic", groupId = "kafka-wiki-consumer-group")
    public void consume(String eventMessage){

        log.info(String.format("Message is -> %s ",eventMessage));
        WikiMediaData wikiMediaData= new WikiMediaData();
        eventMessage=eventMessage.length()>100?eventMessage.substring(0,100):eventMessage;
        wikiMediaData.setWikiData(eventMessage);
        wikiMediaDataRepository.save(wikiMediaData);

    }
}
