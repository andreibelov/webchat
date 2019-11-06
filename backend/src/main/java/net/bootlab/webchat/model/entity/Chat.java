package net.bootlab.webchat.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "chats")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private Date started;
    private String description;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "chat_members",
            joinColumns = {@JoinColumn(
                    name = "fk_chats",
                    referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(
                    name = "fk_users",
                    referencedColumnName="id")}
    )
    @Builder.Default
    private Set<User> members = new HashSet<>();

    @OneToMany(
            cascade = CascadeType.MERGE,
            mappedBy = "chat",
            orphanRemoval = true
    )
    @Builder.Default
    private List<Message> messages = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    public Chat addMember(User member) {
        this.members.add(member);
        member.getChats().add(this);
        return this;
    }

    public Chat removeMember(User member) {
        this.members.remove(member);
        member.getChats().remove(this);
        return this;
    }

    @Getter
    public enum Category {

        CHAT("chat"),
        DIALOGUE("dialog");

        private String type;

        Category(String type) {
            this.type = type;
        }

    }

}
