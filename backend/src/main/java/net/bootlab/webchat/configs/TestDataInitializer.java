package net.bootlab.webchat.configs;

import com.google.common.collect.Sets;
import net.bootlab.webchat.enums.Role;
import net.bootlab.webchat.model.entity.Chat;
import net.bootlab.webchat.model.entity.Message;
import net.bootlab.webchat.model.entity.User;
import net.bootlab.webchat.services.ChatService;
import net.bootlab.webchat.services.MessageService;
import net.bootlab.webchat.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Collections;

@Configuration
public class TestDataInitializer {


    @Bean
    public CommandLineRunner loadData(UserService userService,
                                      ChatService chatService,
                                      MessageService messageService) {
        return (args) -> {

            User user1 = userService.create(new User()
                    .setActive(true)
                    .setEmail("test@test.test")
                    .setFirstName("Алексей")
                    .setLastName("Быков")
                    .setRole(Role.ADMIN_ROLE)
                    .setPassword("password"));

            User user2 = userService.create(new User()
                    .setActive(true)
                    .setEmail("test2@test2.test")
                    .setFirstName("Марья")
                    .setLastName("Андреева")
                    .setRole(Role.USER_ROLE)
                    .setPassword("password1"));


            Chat mainChat = chatService.create(Chat.builder()
                    .category(Chat.Category.CHAT)
                    .description("Main Chat")
                    .build()
                    .addMember(user1)
                    .addMember(user2));

            Message msg1 = messageService.create(Message.builder()
                    .author(user1)
                    .chat(mainChat)
                    .createdAt(LocalDateTime.now())
                    .text("Wello horld!")
                    .build());

        };
    }

}
