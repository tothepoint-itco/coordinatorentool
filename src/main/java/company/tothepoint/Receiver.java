package company.tothepoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.tothepoint.model.Notification;
import company.tothepoint.model.bediende.BediendeCreatedNotification;
import company.tothepoint.model.bediende.BediendeDeletedNotification;
import company.tothepoint.model.bediende.BediendeUpdatedNotification;
import company.tothepoint.model.consultant.Consultant;
import company.tothepoint.repository.ConsultantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

public class Receiver implements MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);


    @Autowired
    private ConsultantRepository consultantRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message) {
        Optional<Notification> notification;

        try {
            notification = Optional.of(objectMapper.readValue(message.getBody(), Notification.class));
        } catch(IOException e ) {
            notification = Optional.empty();
        }

        notification.ifPresent( not -> {

            switch (not.getTitle()) {
                case "bediendeCreated":
                    LOG.debug("EVENT RECEIVED: A bediende was created!");
                    Optional<BediendeCreatedNotification> newBediende;

                    try {
                        newBediende = Optional.of(objectMapper.readValue(message.getBody(), BediendeCreatedNotification.class));
                    } catch (IOException e) {
                        newBediende = Optional.empty();
                    }

                    newBediende.ifPresent( bediendeToCreate -> {
                            Consultant consultantToCreate = new Consultant(
                                    bediendeToCreate.getCreatedBediende().getId(),
                                    bediendeToCreate.getCreatedBediende().getVoorNaam(),
                                    bediendeToCreate.getCreatedBediende().getFamilieNaam(),
                                    bediendeToCreate.getCreatedBediende().getGeboorteDatum()
                                    );
                            consultantRepository.save(consultantToCreate);
                    });
                    break;

                case "bediendeUpdated":
                    LOG.debug("EVENT RECEIVED: A bediende was updated!");
                    Optional<BediendeUpdatedNotification> updatedBediende;

                    try {
                        updatedBediende = Optional.of(objectMapper.readValue(message.getBody(), BediendeUpdatedNotification.class));
                    } catch (IOException e) {
                        updatedBediende = Optional.empty();
                    }

                    updatedBediende.ifPresent( bediendeToUpdate -> {
                        Consultant consultantToCreate = new Consultant(
                                bediendeToUpdate.getUpdatedBediende().getId(),
                                bediendeToUpdate.getUpdatedBediende().getVoorNaam(),
                                bediendeToUpdate.getUpdatedBediende().getFamilieNaam(),
                                bediendeToUpdate.getUpdatedBediende().getGeboorteDatum()
                        );
                        consultantRepository.save(consultantToCreate);
                    });
                    break;

                case "bediendeDeleted":
                    LOG.debug("EVENT RECEIVED: A bediende was deleted!");
                    Optional<BediendeDeletedNotification> deletedBediende;

                    try {
                        deletedBediende = Optional.of(objectMapper.readValue(message.getBody(), BediendeDeletedNotification.class));
                    } catch (IOException e) {
                        deletedBediende = Optional.empty();
                    }

                    deletedBediende.ifPresent( bediendeToDelete ->
                            consultantRepository.delete(bediendeToDelete.getDeletedBediendeId())
                    );

                    break;

                default:
                    LOG.debug("EVENT RECEIVED: Unknown event!");
            }

        });

    }


}
