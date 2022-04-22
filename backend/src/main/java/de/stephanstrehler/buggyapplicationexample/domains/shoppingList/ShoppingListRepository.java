package de.stephanstrehler.buggyapplicationexample.domains.shoppingList;

import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, UUID> {

    boolean existsByName(String name);
}
