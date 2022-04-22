package de.stephanstrehler.buggyapplicationexample.domains.shoppingList.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InputValidationException extends RuntimeException{

    public InputValidationException(
            String message
    ) {
        super(message);
    }
}
