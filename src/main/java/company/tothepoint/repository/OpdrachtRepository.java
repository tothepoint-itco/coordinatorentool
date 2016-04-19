package company.tothepoint.repository;

import company.tothepoint.model.opdracht.Opdracht;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OpdrachtRepository extends MongoRepository<Opdracht, String> {
}
