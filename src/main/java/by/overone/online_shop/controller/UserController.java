package by.overone.online_shop.controller;

import by.overone.online_shop.dao.UserDAO;
import by.overone.online_shop.dto.*;
import by.overone.online_shop.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserDAO userDAO;

//    @GetMapping
//    public List<UserDTO> readAll() {
//        return userService.getAllUsers();
//    }

//    @GetMapping("/{status}")
//    public List<UserDTO> getAllUserByStatus(@PathVariable String status){
//        return userService.getAllUsersByStatus(status);
//    }


    @GetMapping
    public List<UserDTO> findUser(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "surname", required = false) String surname,
                                  @RequestParam(value = "status", required = false) String status) {
        if ((name == null) && (surname == null) && (status == null)){
            return userService.getAllUsers();
        }
        if ((name == null) && (surname == null)){
            return userService.getAllUsersByStatus(status);
        }
        if (status==null){
            return userService.getUserByFullname(name, surname);
        }
            return null;
    }




    @GetMapping("/id/{id}")
public UserDTO getUserById(@PathVariable long id){
    return userService.getUserById(id);
}

    @GetMapping("/detail/{Id}")
    public UserDetailDTO getUserDetailById(@PathVariable long users_id) {
        return userService.getUserDetailById(users_id);
    }

    @GetMapping("/allDetails/{Id}")
    public UserAllDetailsDTO getUserAllDetailsById(@PathVariable long id) {
        return userService.getUserAllDetailsById(id);
    }

    @GetMapping("/{login}")
    public UserDTO getUserByLogin(@PathVariable String login){
        return userService.getUserByLogin(login);
    }

    @GetMapping("/{email}")
    public UserDTO getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@Validated @RequestBody UserRegistretionDTO userRegistretionDTO) {
        userService.addUser(userRegistretionDTO);
    }


    @DeleteMapping("/{id}")
    public void daleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

//    @PatchMapping
//    public void updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
//        userService.userUpdate(userUpdateDTO);
//    }
//
//    @PatchMapping("/details")
//    public void updateDetailsUser(@RequestBody UserDetailUpdateDTO userDetailUpdateDTO) {
//        userService.userDetailUpdate(userDetailUpdateDTO);
//    }


}
