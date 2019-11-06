package net.bootlab.webchat.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@EqualsAndHashCode(of = "id")
@Builder
@ToString
@Accessors(chain = true)
public class ChatDto {

    private Long id;
    private String category;
    private String description;
    @JsonManagedReference
    private Set<UserDto> members = new HashSet<>();
    private List<MessageDto> messages = new ArrayList<>();

}
