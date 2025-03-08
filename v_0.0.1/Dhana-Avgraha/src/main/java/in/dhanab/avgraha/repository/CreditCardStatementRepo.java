package in.dhanab.avgraha.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.dhanab.avgraha.collections.CreditCardStatement;

@Repository
public interface CreditCardStatementRepo extends MongoRepository<CreditCardStatement, String> {
}
