package de.stephanstrehler.buggyapplicationexample.domains.shoppingList;

import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.exceptions.NotFoundException;
import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.model.ShoppingList;
import de.stephanstrehler.buggyapplicationexample.domains.shoppingList.model.ShoppingListItem;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ShoppingListServiceTest {

    private final ShoppingListRepository shoppingListRepository = Mockito.mock(ShoppingListRepository.class);
    private final ShoppingListItemRepository shoppingListItemRepository = Mockito.mock(ShoppingListItemRepository.class);
    private final ShoppingListService shoppingListService = new ShoppingListService(
            shoppingListRepository,
            shoppingListItemRepository
    );

    @Test
    public void addListItemShouldThrowExceptionIfListDoesNotExist() {
        var listId = UUID.randomUUID();

        when(shoppingListRepository.findById(listId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> {
            shoppingListService.addListItem(listId, "name", 1D);
        });
    }

    @Test
    public void addListItemShouldAddListItemIfInputIsCorrect() {
        var listId = UUID.randomUUID();
        var shoppingList = new ShoppingList(
                listId,
                "list name"
        );

        when(shoppingListRepository.findById(listId)).thenReturn(Optional.of(shoppingList));
        when(shoppingListItemRepository.existsByName(eq("Tomate"))).thenReturn(false);
        when(shoppingListItemRepository.save(any())).thenAnswer(new Answer<ShoppingListItem>() {
            @Override
            public ShoppingListItem answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (ShoppingListItem) args[0];
            }
        });

        var result = shoppingListService.addListItem(listId, "Tomate", 1D);
        Assertions.assertEquals("Tomate", result.getName());
        Assertions.assertEquals(1D, result.getAmount());
        Assertions.assertEquals(listId, result.getShoppingListId());
    }
}
