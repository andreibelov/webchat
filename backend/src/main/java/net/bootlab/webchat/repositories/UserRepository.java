package net.bootlab.webchat.repositories;

import io.swagger.annotations.Api;
import net.bootlab.webchat.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Api(tags = "user repository",
        consumes = "application/json",
        produces = "application/json")
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase(String email);
    Optional<User> findById(@Param("id") Long id);
}
