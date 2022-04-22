package de.stephanstrehler.buggyapplicationexample.domains.shoppingList;

import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.model.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, UUID> {
    List<ShoppingListItem> findAllByShoppingListId(UUID shoppingListId);
    boolean existsByName(String name);
}
