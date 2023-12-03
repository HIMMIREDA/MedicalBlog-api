package com.ensa.medicalblog.repository;

import com.ensa.medicalblog.entity.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token,String> {

     @Query("{'user.id': ?0, 'expired': false, 'revoked': false}")
     List<Token> findAllValidTokensByUser(String UserId);

     Optional<Token> findByToken(String Token);

}
