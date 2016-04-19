package company.tothepoint.controller;

import company.tothepoint.model.consultant.*;
import company.tothepoint.repository.ConsultantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultants")
public class ConsultantController {
    private static final Logger LOG = LoggerFactory.getLogger(ConsultantController.class);
    @Autowired
    private ConsultantRepository consultantRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Consultant>> getAllConsultants() {
        LOG.debug("GET /consultants getAllConsultants() called!");
        return new ResponseEntity<>(consultantRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Consultant> getConsultant(@PathVariable("id") String id) {
        LOG.debug("GET /consultants/"+id+" getConsultant("+id+") called!");
        Optional<Consultant> consultantOption = Optional.ofNullable(consultantRepository.findOne(id));

        return consultantOption.map(consultant->
            new ResponseEntity<>(consultant, HttpStatus.OK)
        ).orElse(
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Consultant> createConsultant(@RequestBody Consultant consultant) {
        LOG.debug("POST /consultants createConsultant(..) called!");
        Consultant createdConsultant = consultantRepository.save(consultant);
        return new ResponseEntity<>(createdConsultant, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Consultant> updateConsultant(@PathVariable("id") String id, @RequestBody Consultant consultant) {
        LOG.debug("PUT /consultants/"+id+" updateConsultant("+id+", ..) called!");
        Optional<Consultant> existingConsultant = Optional.ofNullable(consultantRepository.findOne(id));

        return existingConsultant.map(bu ->
            {
                consultant.setId(id);
                return new ResponseEntity<>(consultantRepository.save(consultant), HttpStatus.OK);
            }
        ).orElse(
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Consultant> deleteConsultant(@PathVariable("id") String id) {
        LOG.debug("DELETE /consultants/"+id+" deleteConsultant("+id+") called!");
        if (consultantRepository.exists(id)) {
            consultantRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
