package de.stephanstrehler.buggyapplicationexample.web.model;

public class AddShoppingListBody {

    private String name;

    public AddShoppingListBody() {
    }

    public AddShoppingListBody(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
