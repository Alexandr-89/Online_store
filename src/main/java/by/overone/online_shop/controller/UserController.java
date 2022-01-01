package by.overone.online_shop.controller;

import by.overone.online_shop.dao.UserDAO;
import by.overone.online_shop.dto.UserDTO;
import by.overone.online_shop.dto.UserRegistretionDTO;
import by.overone.online_shop.dto.UserUpdateDTO;
import by.overone.online_shop.model.User;
import by.overone.online_shop.service.UserService;
import by.overone.online_shop.validator.exception.ValidatorException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserDAO userDAO;

    @GetMapping("/all")
    public List<UserDTO> readAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/byStatus")
    public List<UserDTO> getAllUserByStatus(@RequestParam String status){
        return userService.getAllActiveUsers(status);
    }

    @GetMapping("/byId")
    public UserDTO getUserById(@RequestParam long id){
        return userService.getUserById(id);
    }

    @GetMapping("/byLogin")
    public UserDTO getUserByLogin(@RequestParam String login){
        return userService.getUserByLogin(login);
    }

    @GetMapping("/byEmail")
    public UserDTO getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserRegistretionDTO userRegistretionDTO){
        userService.addUser(userRegistretionDTO);
    }

    @GetMapping("/delete")
    public void daleteUser(@RequestParam long id){
        userService.deleteUser(id);
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        userService.userUpdate(userUpdateDTO);
    }


    @GetMapping("/hello")
    public String read() {
        return "hello";
    }

}
