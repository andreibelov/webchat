package net.bootlab.webchat.repositories;

import io.swagger.annotations.Api;
import net.bootlab.webchat.model.entity.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


@Api(tags = "chat repository",
        consumes = "application/json",
        produces = "application/json")
public interface ChatRepository extends CrudRepository<Chat, Long> {

    Optional<Chat> findById(Long id);

}
