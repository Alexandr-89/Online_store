package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.UserDAO;
import by.overone.online_shop.dto.UserDTO;
import by.overone.online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOs = userDAO.getAllUsers()
                .stream().map(user -> new UserDTO(user.getId(), user.getLogin(), user.getEmail(), user.getRole(), user.getStatus()))
                .collect(Collectors.toList());
        return userDTOs;
    }
}
