package company.tothepoint.controller;

import company.tothepoint.model.bestelbon.*;
import company.tothepoint.repository.BestelbonRepository;
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
@RequestMapping("/bestelbonnen")
public class BestelbonController {
    private static final Logger LOG = LoggerFactory.getLogger(BestelbonController.class);
    @Autowired
    private BestelbonRepository bestelbonRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Bestelbon>> getAllBestelbons() {
        LOG.debug("GET /bestelbonnen getAllBestelbons() called!");
        return new ResponseEntity<>(bestelbonRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Bestelbon> getBestelbon(@PathVariable("id") String id) {
        LOG.debug("GET /bestelbonnen/"+id+" getBestelbon("+id+") called!");
        Optional<Bestelbon> bestelbonOption = Optional.ofNullable(bestelbonRepository.findOne(id));

        return bestelbonOption.map(bestelbon->
                new ResponseEntity<>(bestelbon, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Bestelbon> createBestelbon(@RequestBody Bestelbon bestelbon) {
        LOG.debug("POST /bestelbonnen createBestelbon(..) called!");
        Bestelbon createdBestelbon = bestelbonRepository.save(bestelbon);
        return new ResponseEntity<>(createdBestelbon, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Bestelbon> updateBestelbon(@PathVariable("id") String id, @RequestBody Bestelbon bestelbon) {
        LOG.debug("PUT /bestelbonnen/"+id+" updateBestelbon("+id+", ..) called!");
        Optional<Bestelbon> existingBestelbon = Optional.ofNullable(bestelbonRepository.findOne(id));

        return existingBestelbon.map(bu ->
                {
                    bestelbon.setId(id);
                    return new ResponseEntity<>(bestelbonRepository.save(bestelbon), HttpStatus.OK);
                }
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Bestelbon> deleteBestelbon(@PathVariable("id") String id) {
        LOG.debug("DELETE /bestelbonnen/"+id+" deleteBestelbon("+id+") called!");
        if (bestelbonRepository.exists(id)) {
            bestelbonRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
