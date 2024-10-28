package com.forttiori.authtoken.infrastructure.repository.shoppingcart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "shopping-cart")
public class ShoppingCartEntity {
    @Id
    private String shoppingCartId;
    @Indexed(unique = true)
    private String userId;
    private List<String> products;
}
