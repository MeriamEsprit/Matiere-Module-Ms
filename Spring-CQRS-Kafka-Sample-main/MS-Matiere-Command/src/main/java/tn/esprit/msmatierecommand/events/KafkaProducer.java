package tn.esprit.msmatierecommand.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.dto.Event;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {


    private final KafkaTemplate<String, Event> kafkaTemplate;
    private String topic = "matiere-event-topic";

    public void produceEvent(Event matiereEvent) {
        kafkaTemplate.send(this.topic, matiereEvent.type().toString() , matiereEvent);
    }

}
