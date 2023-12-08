package com.ensa.medicalblog.repository;

import com.ensa.medicalblog.entity.TokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<TokenEntity,String> {

     @Query("{'user.id': ?0, 'expired': false, 'revoked': false}")
     List<TokenEntity> findAllValidTokensByUser(String UserId);

     Optional<TokenEntity> findByToken(String Token);

}
