package net.bootlab.webchat.repositories;

import io.swagger.annotations.Api;
import net.bootlab.webchat.model.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

@Api(tags = "message repository",
        consumes = "application/json",
        produces = "application/json")
public interface MessageRepository extends CrudRepository<Message, Long> {

    Optional<Message> findById(Long id);
    Optional<Message> findByUuid(UUID uuid);

}
