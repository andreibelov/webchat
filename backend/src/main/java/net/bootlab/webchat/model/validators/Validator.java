package net.bootlab.webchat.model.validators;

import net.bootlab.webchat.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Validator {

    public void validateUser(UserDto user) {
        Objects.requireNonNull(user, "user should be not null");
    }

    public void validateId(Long id) {
        Objects.requireNonNull(id, "id should be not null");
    }
}
