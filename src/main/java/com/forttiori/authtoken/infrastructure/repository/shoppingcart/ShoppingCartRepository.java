package com.forttiori.authtoken.infrastructure.repository.shoppingcart;

import com.forttiori.authtoken.infrastructure.repository.user.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCartEntity, String> {

    Optional<ShoppingCartEntity> findByUserId(String userId);
}
