package net.bootlab.webchat.controllers;

import lombok.RequiredArgsConstructor;
import net.bootlab.webchat.model.dto.UserDto;
import net.bootlab.webchat.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static net.bootlab.webchat.configs.Endpoints.API_USERS;

@RequestMapping(API_USERS)
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> listAll() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

}
