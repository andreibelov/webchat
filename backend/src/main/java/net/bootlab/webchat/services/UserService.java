package net.bootlab.webchat.services;

import com.google.common.base.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bootlab.webchat.exceptions.UserNotFoundException;
import net.bootlab.webchat.model.dto.UserDto;
import net.bootlab.webchat.model.entity.User;
import net.bootlab.webchat.model.validators.Validator;
import net.bootlab.webchat.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final Validator validator;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public List<UserDto> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        validator.validateId(id);
        return this.toDto(getUserById(id));
    }



    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Cannot find user with id " + id));
    }

    private UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User create(User user1) {
        return userRepository.save(user1);
    }
}
