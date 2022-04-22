package de.stephanstrehler.buggyapplicationexample.web;

import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.ShoppingListService;
import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.exceptions.InputValidationException;
import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.model.ShoppingList;
import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.model.ShoppingListItem;
import de.stephanstrehler.buggyapplicationexample.web.model.AddShoppingListBody;
import de.stephanstrehler.buggyapplicationexample.web.model.AddShoppingListItemBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/list")
public class ListController {

    private final ShoppingListService shoppingListService;

    public ListController(
            ShoppingListService shoppingListService
    ) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping
    public List<ShoppingList> getShoppingLists() {
        return shoppingListService.getLists();
    }

    @GetMapping("/{id}")
    public ShoppingList getShoppingList(@PathVariable UUID id) {
        return shoppingListService.getList(id);
    }

    @PostMapping
    public ShoppingList addShoppingList(@RequestBody AddShoppingListBody body) throws InputValidationException {
        return shoppingListService.addList(body.getName());
    }
    @GetMapping("/{listId}/items")
    public List<ShoppingListItem> getShoppingListItems(@PathVariable UUID listId) {
        return shoppingListService.getListItems(listId);
    }
    @PostMapping("/{listId}/items")
    public ShoppingListItem addShoppingListItem(@PathVariable UUID listId, @RequestBody AddShoppingListItemBody body) throws InputValidationException {
        return shoppingListService.addListItem(listId, body.getName(), body.getAmount());
    }
}
