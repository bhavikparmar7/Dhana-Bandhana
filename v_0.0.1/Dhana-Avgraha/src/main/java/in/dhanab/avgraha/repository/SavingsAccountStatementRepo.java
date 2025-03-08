package in.dhanab.avgraha.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.dhanab.avgraha.collections.SavingsAccountStatement;

@Repository
public interface SavingsAccountStatementRepo extends MongoRepository<SavingsAccountStatement, String> {
}
