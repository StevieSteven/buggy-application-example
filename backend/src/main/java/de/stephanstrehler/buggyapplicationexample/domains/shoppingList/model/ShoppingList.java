package de.stephanstrehler.buggyapplicationexample.domains.shoppingList.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class ShoppingList {
    @Id
    private UUID id;
    private String name;

    public ShoppingList() {
        // required for Java persistence
    }

    public ShoppingList(UUID id, String name) {
        this.id = id;
        this.name = name;
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

}
