package net.bootlab.webchat.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(
        name = "messages",
        indexes = {
                @Index(
                        columnList = "uuid",
                        name = "message_uuid_idx"
                )
        }
)
@EqualsAndHashCode(of = "uuid")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Message {

    @Id
    @GeneratedValue(
            generator = "uuid2",
            strategy = GenerationType.IDENTITY
    )
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(
            name = "uuid",
            unique = true,
            columnDefinition = "BINARY(16)"
    )
    private UUID uuid;
    private String text;

    @NotNull
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime createdAt;
    @Builder.Default private boolean isSpam = false;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

}
