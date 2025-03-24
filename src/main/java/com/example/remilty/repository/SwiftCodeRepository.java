package com.example.remilty.repository;

import com.example.remilty.model.SwiftCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SwiftCodeRepository extends MongoRepository<SwiftCode, String> {

    Optional<SwiftCode> getBySwiftCode(String swiftCode);
    @Query("{'_id': { $regex: ?0, $options: i, $not: { $regex: 'XXX$' }}}")
    List<SwiftCode> getBranches(String swiftCode);
    List<SwiftCode> getByCountryISO2(String countryCode);

}
