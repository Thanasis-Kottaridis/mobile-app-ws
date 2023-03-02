package gr.deque.mobile.app.ws.controller;

import gr.deque.mobile.app.ws.dto.UserDetailsRequestDto;
import gr.deque.mobile.app.ws.common.utils.StringUtils;
import gr.deque.mobile.app.ws.dto.UserDto;
import gr.deque.mobile.app.ws.mapper.UserDetailsRequestMapper;
import gr.deque.mobile.app.ws.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

    static ArrayList<User> mockUsers = new ArrayList<User>(){{
       add(new User("1", "test1@mail.com","Name1", "Surname1"));
       add(new User("2", "test2@mail.com","Name2", "Surname2"));
       add(new User("3", "test3@mail.com","Name3", "Surname3"));
       add(new User("4", "test4@mail.com","Name4", "Surname4"));
       add(new User("5", "test5@mail.com","Name5", "Surname5"));
       add(new User("6", "test6@mail.com","Name6", "Surname6"));
       add(new User("7", "test7@mail.com","Name7", "Surname7"));
       add(new User("8", "test8@mail.com","Name8", "Surname8"));
       add(new User("9", "test9@mail.com","Name9", "Surname9"));
       add(new User("0", "test0@mail.com","Name0", "Surname0"));
       add(new User("11", "test11@mail.com","Name11", "Surname11"));
       add(new User("12", "test12@mail.com","Name12", "Surname12"));
       add(new User("13", "test13@mail.com","Name13", "Surname13"));
       add(new User("14", "test14@mail.com","Name14", "Surname14"));
       add(new User("15", "test15@mail.com","Name15", "Surname15"));
       add(new User("16", "test16@mail.com","Name16", "Surname16"));
       add(new User("17", "test17@mail.com","Name17", "Surname17"));
       add(new User("18", "test18@mail.com","Name18", "Surname18"));
       add(new User("19", "test19@mail.com","Name19", "Surname19"));
       add(new User("10", "test10@mail.com","Name10", "Surname10"));
       add(new User("21", "test21@mail.com","Name21", "Surname21"));
       add(new User("22", "test22@mail.com","Name22", "Surname22"));
       add(new User("23", "test23@mail.com","Name23", "Surname23"));
       add(new User("24", "test24@mail.com","Name24", "Surname24"));
       add(new User("25", "test25@mail.com","Name25", "Surname25"));
       add(new User("26", "test26@mail.com","Name26", "Surname26"));
       add(new User("27", "test27@mail.com","Name27", "Surname27"));
       add(new User("28", "test28@mail.com","Name28", "Surname28"));
       add(new User("29", "test29@mail.com","Name29", "Surname29"));
       add(new User("20", "test20@mail.com","Name20", "Surname20"));
    }};

    /*
       Ama theloume na perasoume request parameter sto GET endpoint
       xrisimopoioume `@RequestParam` sta arguments tis method.

       - Sto `value` vazoume to onoma tis parametrou
       - Sto default value mporoume na valoume opoiadipote
         timi gia na ginei to param optional
       - Episis mporoume na to kanoume required true / false
    */
    @GetMapping
    public List<User> getUsers(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", required = false) String sort
    ) {
        try {

            var pageIndex = page*10;
            return mockUsers.subList(pageIndex, pageIndex + size);
        }
        catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Exception thrown : " + e);
            return List.of();
        }
    }

    /*
        Ama theloume na perasoume path parametest sto GET enpoint
        xrisimopoioume to path argument tou ` @GetMapping`

        @Note
        1. path parameter has to be in curly braces `path = "/{userId}"`
        2. If we want to support returning multiple content format (eg. xml or json)
           we need to use produces configuration
        3. In order to return Custom Repose codes we have to use `ResponseEntity`
     */
    @GetMapping(
            path = "/{userId}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        return mockUsers.stream()
                .filter(user -> Objects.equals(user.getUserId(), userId))
                .findFirst()
                .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<User> createUser(
            @RequestBody UserDetailsRequestDto body
    ) {
        var newUser = UserDetailsRequestMapper.INSTANCE.domainToModel(body);
        mockUsers.add(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}")
    public User updateUser(
            @PathVariable String userId,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "firstName", required = false) String firstName
    ) {

        var index = IntStream.range(0, mockUsers.size())
                .filter(i -> Objects.equals(mockUsers.get(i).getUserId(), userId))
                .findFirst()
                .orElse(-1);

        var existingUser = mockUsers.get(index);

        mockUsers.set(
                index,
                new User(
                        userId,
                        StringUtils.isNullOrEmpty(email) ? existingUser.getEmail() : email,
                        StringUtils.isNullOrEmpty(firstName) ? existingUser.getFirstName() : firstName,
                        StringUtils.isNullOrEmpty(lastName) ? existingUser.getLastName() : lastName
                ));

        return mockUsers.get(index);
    }

    @DeleteMapping(path = "/{userId}")
    public String deleteUser(@PathVariable String userId) {
        mockUsers.removeIf(userDto -> Objects.equals(userDto.getUserId(), userId));
        return "deleteUser called with id => %s. new mockUsers Size is => %s".formatted(userId, mockUsers.size());
    }
}