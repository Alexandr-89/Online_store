package by.overone.online_shop.service;

import by.overone.online_shop.dto.UserDTO;
import by.overone.online_shop.dto.UserRegistretionDTO;
import by.overone.online_shop.validator.exception.ValidatorException;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    void addUser(UserRegistretionDTO userRegistretionDTO) throws ValidatorException;
}
