package company.tothepoint.repository;

import company.tothepoint.model.consultant.Consultant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsultantRepository extends MongoRepository<Consultant, String> {
}
