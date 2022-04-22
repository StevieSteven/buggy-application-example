package de.stephanstrehler.buggyapplicationexample.domains.shoppingList.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Objekt not found")
public class NotFoundException extends RuntimeException {
}
