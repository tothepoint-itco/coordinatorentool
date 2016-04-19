package company.tothepoint.repository;

import company.tothepoint.model.akkoord.Akkoord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AkkoordRepository extends MongoRepository<Akkoord, String> {
}
