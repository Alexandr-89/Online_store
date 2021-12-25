package by.overone.online_shop.controller;

import by.overone.online_shop.dto.UserDTO;
import by.overone.online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> readAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/hello")
    public String read(){
        return "hello";
    }

}
