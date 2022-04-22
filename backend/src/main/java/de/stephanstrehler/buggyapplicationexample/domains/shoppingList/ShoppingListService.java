package de.stephanstrehler.buggyapplicationexample.domains.shoppingList;

import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.exceptions.InputValidationException;
import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.exceptions.NotFoundException;
import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.model.ShoppingList;
import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.model.ShoppingListItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListItemRepository shoppingListItemRepository;

    public ShoppingListService(
            ShoppingListRepository shoppingListRepository,
            ShoppingListItemRepository shoppingListItemRepository
    ) {
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListItemRepository = shoppingListItemRepository;
    }

    public List<ShoppingList> getLists() {
        return shoppingListRepository.findAll();
    }

    public ShoppingList addList(String name) throws InputValidationException {
        if (name.isBlank()) {
            throw new InputValidationException("name must not be empty");
        }

        if (shoppingListRepository.existsByName(name.trim())) {
            throw new InputValidationException("list with name still exists");
        }
        return shoppingListRepository.save(new ShoppingList(
                UUID.randomUUID(),
                name.trim()
        ));
    }

    public ShoppingList getList(UUID id) {
        return shoppingListRepository.getById(id);
    }

    public List<ShoppingListItem> getListItems(UUID id) {
        if(!shoppingListRepository.existsById(id)) {
            throw new NotFoundException();
        }
        return shoppingListItemRepository.findAllByShoppingListId(id);
    }

    public ShoppingListItem addListItem(UUID listId, String name, Double amount) {
        var optionalList = shoppingListRepository.findById(listId);
        if (optionalList.isEmpty()) {
            throw new NotFoundException();
        }
        var list = optionalList.get();

        if (shoppingListItemRepository.existsByName(name.trim())) {
            throw new InputValidationException("list with name still exists");
        }

        var newItem = new ShoppingListItem(
                UUID.randomUUID(),
                name,
                amount,
                list.getId()
        );
        return shoppingListItemRepository.save(newItem);
    }
}
