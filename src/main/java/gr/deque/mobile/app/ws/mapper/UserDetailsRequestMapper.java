package gr.deque.mobile.app.ws.mapper;

import gr.deque.mobile.app.ws.common.helpers.DomainMapper;
import gr.deque.mobile.app.ws.dto.UserDetailsRequestDto;
import gr.deque.mobile.app.ws.dto.UserDto;
import gr.deque.mobile.app.ws.model.User;

import java.util.UUID;

public class UserDetailsRequestMapper implements DomainMapper<User, UserDetailsRequestDto> {

    public static UserDetailsRequestMapper INSTANCE = new UserDetailsRequestMapper();

    @Override
    public UserDetailsRequestDto modelToDomain(User user) {
        return new UserDetailsRequestDto(
                user.getUserId(),
                user.getEmail(),
                user.getFirstName(),
                null
        );
    }

    @Override
    public User domainToModel(UserDetailsRequestDto userDto) {
        return new User(
                UUID.randomUUID().toString(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName()
        );
    }
}
