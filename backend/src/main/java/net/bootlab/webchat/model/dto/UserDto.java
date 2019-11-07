package net.bootlab.webchat.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.Accessors;
import net.bootlab.webchat.enums.Role;
import net.bootlab.webchat.enums.Sex;
import net.bootlab.webchat.model.validators.EmailConstraint;

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
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;

    @EmailConstraint
    private String email;

    @JsonIgnore
    private String password;
    private Role role;
    private Sex sex;

}
