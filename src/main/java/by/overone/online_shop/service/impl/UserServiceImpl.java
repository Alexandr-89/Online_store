package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.UserDAO;
import by.overone.online_shop.dto.*;
import by.overone.online_shop.model.Status;
import by.overone.online_shop.model.User;
import by.overone.online_shop.model.UserDetail;
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
    public void addUser(UserRegistretionDTO userRegistretionDTO) {
        UserValidator.validatorUserRegistrationDTO(userRegistretionDTO);
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
    public UserDetailDTO getUserDetailById(long users_id) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        UserDetail userDetail = userDAO.getUserDetailByUserId(users_id);
        userDetailDTO.setName(userDetail.getName());
        userDetailDTO.setSurname(userDetail.getSurname());
        userDetailDTO.setAddress(userDetail.getAddress());
        userDetailDTO.setPhone(userDetail.getPhone());
        return userDetailDTO;
    }

    @Override
    public UserAllDetailsDTO getUserAllDetailsById(long id) {
        return userDAO.getUserAllDetailsById(id);
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
        User user = userDAO.getUserById(userUpdateDTO.getId());
        if (userUpdateDTO.getLogin()!=null){
            userUpdateDTO.setLogin(userUpdateDTO.getLogin());
        }else {
            userUpdateDTO.setLogin(user.getLogin());
        }
        if (userUpdateDTO.getPassword()!=null){
            userUpdateDTO.setPassword(userUpdateDTO.getPassword());
        }else {
            userUpdateDTO.setPassword(user.getPassword());
        }
        if (userUpdateDTO.getEmail()!=null){
            userUpdateDTO.setEmail(userUpdateDTO.getEmail());
        }else {
            userUpdateDTO.setEmail(user.getEmail());
        }
        userUpdateDTO.setRole(user.getRole());
        userUpdateDTO.setStatus(user.getStatus());
//        if (userUpdateDTO.getName()!=null){
//            userUpdateDTO.setName(userUpdateDTO.getName());
//        }else {
//            userUpdateDTO.setName(userAllDetailsDTO.getName());
//        }
//        if (userUpdateDTO.getSurname()!=null){
//            userUpdateDTO.setSurname(userUpdateDTO.getSurname());
//        }else {
//            userUpdateDTO.setSurname(userAllDetailsDTO.getSurname());
//        }
//        if (userUpdateDTO.getAddress()!=null){
//            userUpdateDTO.setAddress(userUpdateDTO.getAddress());
//        }else {
//            userUpdateDTO.setAddress(userAllDetailsDTO.getAddress());
//        }
//        if (userUpdateDTO.getPhone()!=null){
//            userUpdateDTO.setPhone(userUpdateDTO.getPhone());
//        }else {
//            userUpdateDTO.setPhone(userAllDetailsDTO.getPhone());
//        }
        userDAO.updateUser(userUpdateDTO);
    }

    @Override
    public void userDetailUpdate(UserDetailUpdateDTO userDetailUpdateDTO) {
        UserDetail userDetail = userDAO.getUserDetailByUserId(userDetailUpdateDTO.getUsers_id());
        if (userDetailUpdateDTO.getName()!=null){
            userDetailUpdateDTO.setName(userDetailUpdateDTO.getName());
        }else {
            userDetailUpdateDTO.setName(userDetail.getName());
        }
        if (userDetailUpdateDTO.getSurname()!=null){
            userDetailUpdateDTO.setSurname(userDetailUpdateDTO.getSurname());
        }else {
            userDetailUpdateDTO.setSurname(userDetail.getSurname());
        }
        if (userDetailUpdateDTO.getAddress()!=null){
            userDetailUpdateDTO.setAddress(userDetailUpdateDTO.getAddress());
        }else {
            userDetailUpdateDTO.setAddress(userDetail.getAddress());
        }
        if (userDetailUpdateDTO.getPhone()!=null){
            userDetailUpdateDTO.setPhone(userDetailUpdateDTO.getPhone());
        }else {
            userDetailUpdateDTO.setPhone(userDetail.getPhone());
        }
        userDAO.updateUserDetails(userDetailUpdateDTO);
    }


}
