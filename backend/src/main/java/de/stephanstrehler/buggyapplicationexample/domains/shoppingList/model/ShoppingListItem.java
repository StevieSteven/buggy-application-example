package de.stephanstrehler.buggyapplicationexample.domains.shoppingList.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class ShoppingListItem {
    @Id
    private UUID id;
    private String name;
    private Double amount;
    private UUID shoppingListId;

    public ShoppingListItem() {
    }

    public ShoppingListItem(UUID id, String name, Double amount, UUID shoppingListId) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.shoppingListId = shoppingListId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
}
