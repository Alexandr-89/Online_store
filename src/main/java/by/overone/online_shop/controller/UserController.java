package by.overone.online_shop.controller;

import by.overone.online_shop.dao.UserDAO;
import by.overone.online_shop.dto.*;
import by.overone.online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@Valid @Min(1) @PathVariable("id") Long id){
        return userService.getUserById(id);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findUsers(@RequestBody UserForGetDTO userForGetDTO){
        return userService.findUsers(userForGetDTO);
    }


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserAllInfoDTO> findUsersAllInfo(@RequestBody UserForGetDTO userForGetDTO){
        return userService.findUsersAllInfo(userForGetDTO);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@Validated @RequestBody UserRegistrationDTO userRegistrationDTO) {
        userService.addUser(userRegistrationDTO);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("id") Long id, @Validated @RequestBody UserUpdateDTO userUpdateDTO) {
        userService.userUpdate(id, userUpdateDTO);
    }


    @PatchMapping("/details/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDetailsUser(@PathVariable("userId") Long userId, @Validated @RequestBody UserDetailUpdateDTO userDetailUpdateDTO) {
        userService.userDetailUpdate(userId, userDetailUpdateDTO);
    }

}
