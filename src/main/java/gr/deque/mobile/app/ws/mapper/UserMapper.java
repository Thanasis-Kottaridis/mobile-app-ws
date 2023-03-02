package gr.deque.mobile.app.ws.mapper;

import gr.deque.mobile.app.ws.common.helpers.DomainMapper;
import gr.deque.mobile.app.ws.dto.UserDto;
import gr.deque.mobile.app.ws.model.User;

public class UserMapper implements DomainMapper<User, UserDto> {

    public static UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto modelToDomain(User user) {
        return new UserDto(
                user.getUserId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    @Override
    public User domainToModel(UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName()
        );
    }
}
