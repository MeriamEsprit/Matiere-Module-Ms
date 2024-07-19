package tn.esprit.msmatierequery.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.dto.Event;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MatiereEventHandler matiereEventHandler;
    private final String topic = "matiere-event-topic";

    @KafkaListener(topics = topic, groupId = "my-group")
    public void consume(ConsumerRecord<String, Event> consumerRecord) {

        Event event = consumerRecord.value();

        log.info("\n Consumed Event of type : {} \n published on topic at : {} \n Data value is : {}", event.type(), event.eventCreatedAt(), event.matiereDto() );

        switch (consumerRecord.key()) {
            case "CREATED_MATIERE_EVENT":
                matiereEventHandler.handleMatiereCreatedEvent(event.matiereDto());
                break;
            case "UPDATED_MATIERE_EVENT":
                matiereEventHandler.handleMatiereUpdatedEvent(event.matiereDto());
                break;
            case "DELETED_MATIERE_EVENT":
                matiereEventHandler.handleMatiereDeletedEvent(event.matiereDto().id());
                break;
            default:
                log.info("Event ignored");
                break;
        }

    }
}
