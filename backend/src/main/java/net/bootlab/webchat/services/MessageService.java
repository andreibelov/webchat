package net.bootlab.webchat.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bootlab.webchat.model.entity.Message;
import net.bootlab.webchat.model.validators.Validator;
import net.bootlab.webchat.repositories.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final Validator validator;
    private final ModelMapper modelMapper;
    private final MessageRepository messageRepository;

    public Message create(Message message) {
        return messageRepository.save(message);
    }



}
