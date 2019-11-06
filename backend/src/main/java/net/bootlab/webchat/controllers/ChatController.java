package net.bootlab.webchat.controllers;

import lombok.RequiredArgsConstructor;
import net.bootlab.webchat.model.dto.ChatDto;
import net.bootlab.webchat.services.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static net.bootlab.webchat.configs.Endpoints.API_CHAT;

@RequestMapping(API_CHAT)
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public List<ChatDto> listAll() {
        return chatService.findAll();
    }

    @GetMapping("{id}")
    public ChatDto getUserById(@PathVariable("id") Long id) {
        return chatService.findById(id);
    }

}
