package com.forttiori.authtoken.domain;

import com.forttiori.authtoken.infrastructure.repository.shoppingcart.ShoppingCartEntity;
import com.forttiori.authtoken.infrastructure.repository.shoppingcart.ShoppingCartRepository;
import com.forttiori.authtoken.infrastructure.repository.user.UserEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

//    @PostConstruct
//    void test() {
//        shoppingCartRepository.save(ShoppingCartEntity.builder()
//                        .userId("671fda6735484d6cb33d7fe4")
//                        .products(List.of("I3", "IPHONE", "CARTA DE MAGIC"))
//                .build());
//    }

    public String getShoppingCart() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return shoppingCartRepository.findByUserId(user.getUserId())
                .map(ShoppingCartEntity::toString)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart to found"));
    }
}
