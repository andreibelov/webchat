package net.bootlab.webchat.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "uuid")
@Builder
@ToString
@Accessors(chain = true)
public class MessageDto {

    private UUID uuid;
    private String text;
    @JsonIgnore
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private ChatDto chat;
    private UserDto author;

}
