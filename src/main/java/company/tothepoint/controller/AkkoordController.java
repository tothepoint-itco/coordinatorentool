package company.tothepoint.controller;

import company.tothepoint.model.akkoord.*;
import company.tothepoint.repository.AkkoordRepository;
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
@RequestMapping("/akkoorden")
public class AkkoordController {
    private static final Logger LOG = LoggerFactory.getLogger(AkkoordController.class);
    @Autowired
    private AkkoordRepository akkoordRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Akkoord>> getAllAkkoords() {
        LOG.debug("GET /akkoorden getAllAkkoords() called!");
        return new ResponseEntity<>(akkoordRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Akkoord> getAkkoord(@PathVariable("id") String id) {
        LOG.debug("GET /akkoorden/"+id+" getAkkoord("+id+") called!");
        Optional<Akkoord> akkoordOption = Optional.ofNullable(akkoordRepository.findOne(id));

        return akkoordOption.map(akkoord->
                new ResponseEntity<>(akkoord, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Akkoord> createAkkoord(@RequestBody Akkoord akkoord) {
        LOG.debug("POST /akkoorden createAkkoord(..) called!");
        Akkoord createdAkkoord = akkoordRepository.save(akkoord);
        return new ResponseEntity<>(createdAkkoord, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Akkoord> updateAkkoord(@PathVariable("id") String id, @RequestBody Akkoord akkoord) {
        LOG.debug("PUT /akkoorden/"+id+" updateAkkoord("+id+", ..) called!");
        Optional<Akkoord> existingAkkoord = Optional.ofNullable(akkoordRepository.findOne(id));

        return existingAkkoord.map(bu ->
                {
                    akkoord.setId(id);
                    return new ResponseEntity<>(akkoordRepository.save(akkoord), HttpStatus.OK);
                }
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Akkoord> deleteAkkoord(@PathVariable("id") String id) {
        LOG.debug("DELETE /akkoorden/"+id+" deleteAkkoord("+id+") called!");
        if (akkoordRepository.exists(id)) {
            akkoordRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
