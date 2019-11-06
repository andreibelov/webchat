package net.bootlab.webchat.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bootlab.webchat.exceptions.ChatNotFoundException;
import net.bootlab.webchat.model.dto.ChatDto;
import net.bootlab.webchat.model.entity.Chat;
import net.bootlab.webchat.model.validators.Validator;
import net.bootlab.webchat.repositories.ChatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final Validator validator;
    private final ModelMapper modelMapper;
    private final ChatRepository chatRepository;

    public List<ChatDto> findAll() {
        return StreamSupport.stream(chatRepository.findAll().spliterator(), false)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ChatDto findById(Long id) {
        validator.validateId(id);
        return this.toDto(getChatById(id));
    }

    public Chat getChatById(Long id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new ChatNotFoundException("Cannot find chat with id " + id));
    }

    private ChatDto toDto(Chat chat) {
        return modelMapper.map(chat, ChatDto.class);
    }

    public Chat create(Chat chat) {
        Chat persistedChat = chatRepository.save(chat);

        persistedChat.getMembers()
                .forEach(u -> u.addChat(persistedChat));

        return persistedChat;
    }
}
