package net.bootlab.webchat.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.Accessors;
import net.bootlab.webchat.enums.Role;
import net.bootlab.webchat.enums.Sex;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private Long id;

    private Date birthDate;
    private String firstName;
    private String secondName;
    private String email;
    private String password;

    private boolean active;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER_ROLE;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Sex sex = Sex.UNDEFINED;

    @ManyToMany(
            mappedBy = "members",
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private Set<Chat> chats = new HashSet<>();

    public void addChat(Chat chat) {
        this.chats.add(chat);
        chat.getMembers().add(this);
    }

    public void removeChat(Chat chat) {
        this.chats.remove(chat);
        chat.getMembers().remove(this);
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(this.role.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
