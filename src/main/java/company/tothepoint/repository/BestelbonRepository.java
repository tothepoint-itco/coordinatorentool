package company.tothepoint.repository;

import company.tothepoint.model.bestelbon.Bestelbon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BestelbonRepository extends MongoRepository<Bestelbon, String> {
}
