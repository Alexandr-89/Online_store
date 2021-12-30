package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.UserDAO;
import by.overone.online_shop.dto.UserDTO;
import by.overone.online_shop.dto.UserRegistretionDTO;
import by.overone.online_shop.dto.UserUpdateDTO;
import by.overone.online_shop.model.Status;
import by.overone.online_shop.model.User;
import by.overone.online_shop.service.UserService;
import by.overone.online_shop.validator.UserValidator;
import by.overone.online_shop.validator.exception.ValidatorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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

    @Override
    public List<UserDTO> getAllActiveUsers(String status) {
        List<UserDTO> userDTOs = userDAO.getAllUserByStatus(status)
                .stream().map(user -> new UserDTO(user.getId(), user.getLogin(), user.getEmail(), user.getRole(), user.getStatus()))
                .collect(Collectors.toList());
        return userDTOs;
    }

    @Override
    public void addUser(UserRegistretionDTO userRegistretionDTO) throws ValidatorException {
//        UserValidator.validatorUserRegistrationDTO(userRegistretionDTO);
        User user = new User();
        user.setLogin(userRegistretionDTO.getLogin());
        user.setPassword(userRegistretionDTO.getPassword());
        user.setEmail(userRegistretionDTO.getEmail());
        user.setRole("CUSTOMER");
        user.setStatus("ACTIVE");
        userDAO.addUser(user);
    }

    @Override
    public UserDTO getUserById(long id) {
        UserDTO userDTOs = new UserDTO();
        User user =userDAO.getUserById(id);
        userDTOs.setId(user.getId());
        userDTOs.setLogin(user.getLogin());
        userDTOs.setEmail(user.getEmail());
        userDTOs.setRole(user.getRole());
        userDTOs.setStatus(user.getStatus());
        return userDTOs;
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        UserDTO userDTOs = new UserDTO();
        User user =userDAO.getUserByLogin(login);
        userDTOs.setId(user.getId());
        userDTOs.setLogin(user.getLogin());
        userDTOs.setEmail(user.getEmail());
        userDTOs.setRole(user.getRole());
        userDTOs.setStatus(user.getStatus());
        return userDTOs;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserDTO userDTOs = new UserDTO();
        User user =userDAO.getUserByEmail(email);
        userDTOs.setId(user.getId());
        userDTOs.setLogin(user.getLogin());
        userDTOs.setEmail(user.getEmail());
        userDTOs.setRole(user.getRole());
        userDTOs.setStatus(user.getStatus());
        return userDTOs;
    }

    @Override
    public void deleteUser(long id) {
        getUserById(id);
        userDAO.deleteUser(id);
    }

    @Override
    public void userUpdate(UserUpdateDTO userUpdateDTO) {
        getUserById(userUpdateDTO.getId());
        userDAO.updateUser(userUpdateDTO);
    }


}
