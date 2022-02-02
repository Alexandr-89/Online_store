package by.overone.online_shop.service.impl;

import by.overone.online_shop.dao.UserDAO;
import by.overone.online_shop.dto.*;
import by.overone.online_shop.exception.EntityNotFoundException;
import by.overone.online_shop.exception.ExceptionCode;
import by.overone.online_shop.model.Role;
import by.overone.online_shop.model.Status;
import by.overone.online_shop.model.User;
import by.overone.online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;




//    @Override
//    public List<UserDTO> getAllUsers() {
//        List<User> users = userDAO.getAllUsers();
//        if (users.isEmpty()){
//            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode());
//        }
//        List<UserDTO> userDTOs = userDAO.getAllUsers()
//                .stream().map(user -> new UserDTO( user.getLogin(), user.getEmail()))
//                .collect(Collectors.toList());
//        return userDTOs;
//    }

//    @Override
//    public List<UserDTO> getAllUsersByStatus(String status) {
//        List<User> users = userDAO.getAllUserByStatus(status);
//        if (users.isEmpty()){
//            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode());
//        }
//        List<UserDTO> userDTOs = userDAO.getAllUserByStatus(status)
//                .stream().map(user -> new UserDTO(user.getLogin(), user.getEmail()))
//                .collect(Collectors.toList());
//        return userDTOs;
//    }

//    @Override
//    public List<UserDTO> getUserByFullname(String name, String surname) {
//        List<User> users = userDAO.getUserByFullname(name, surname);
//        if (users.isEmpty()){
//            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode());
//        }
//        List<UserDTO> userDTOs = userDAO.getUserByFullname(name, surname)
//                .stream().map(user -> new UserDTO(user.getLogin(), user.getEmail()))
//                .collect(Collectors.toList());
//        return userDTOs;
//    }

    @Override
    public void addUser(UserRegistrationDTO userRegistretionDTO) {
//        UserValidator.validatorUserRegistrationDTO(userRegistretionDTO);
        User user = new User();
        user.setLogin(userRegistretionDTO.getLogin());
        user.setPassword(userRegistretionDTO.getPassword());
        user.setEmail(userRegistretionDTO.getEmail());
        user.setRole(Role.CUSTOMER);
        user.setStatus(Status.ACTIVE);
        userDAO.addUser(user);
    }

    @Override
    public UserDTO getUserById(long id) {
        UserDTO userDTOs = new UserDTO();
        UserAllDetailsDTO user =userDAO.getUserAllInfoById(id)
                .orElseThrow(()-> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
//        userDTOs.setId(user.getId());
        userDTOs.setLogin(user.getLogin());
        userDTOs.setEmail(user.getEmail());
//        userDTOs.setRole(user.getRole());
//        userDTOs.setStatus(user.getStatus());
        return userDTOs;
    }

//    @Override
//    public UserDetailDTO getUserDetailById(long id) {
//        UserDetailDTO userDetailDTO = new UserDetailDTO();
//        UserAllDetailsDTO user =userDAO.getUserAllInfoById(id)
//                .orElseThrow(()-> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
//        userDetailDTO.setName(user.getName());
//        userDetailDTO.setSurname(user.getSurname());
//        userDetailDTO.setAddress(user.getAddress());
//        userDetailDTO.setPhone(user.getPhone());
//        return userDetailDTO;
//    }

//    @Override
//    public UserAllDetailsDTO getUserAllDetailsById(long id) {
//        return userDAO.getUserAllDetailsById(id);
//    }
//
//    @Override
//    public UserDTO getUserByLogin(String login) {
//        UserDTO userDTOs = new UserDTO();
//        User user =userDAO.getUserByLogin(login);
////        userDTOs.setId(user.getId());
//        userDTOs.setLogin(user.getLogin());
//        userDTOs.setEmail(user.getEmail());
////        userDTOs.setRole(user.getRole());
////        userDTOs.setStatus(user.getStatus());
//        return userDTOs;
//    }

//    @Override
//    public UserDTO getUserByEmail(String email) {
//        UserDTO userDTOs = new UserDTO();
//        User user =userDAO.getUserByEmail(email);
////        userDTOs.setId(user.getId());
//        userDTOs.setLogin(user.getLogin());
//        userDTOs.setEmail(user.getEmail());
////        userDTOs.setRole(user.getRole());
////        userDTOs.setStatus(user.getStatus());
//        return userDTOs;
//    }

    @Override
    public void deleteUser(long id) {
        getUserById(id);
        userDAO.deleteUser(id);
    }

    @Override
    public void userUpdate(long id, UserUpdateDTO userUpdateDTO) {
        UserAllDetailsDTO user = userDAO.getUserAllInfoById(id).orElseThrow(()->
                new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
        if (userUpdateDTO.getLogin()!=null){
            user.setLogin(userUpdateDTO.getLogin());
        }
        if (userUpdateDTO.getPassword()!=null){
            user.setPassword(userUpdateDTO.getPassword());
        }
        if (userUpdateDTO.getEmail()!=null){
            user.setEmail(userUpdateDTO.getEmail());
        }

        userDAO.updateUser(id, user);
    }

    @Override
    public void userDetailUpdate(long id, UserDetailUpdateDTO userDetailUpdateDTO) {
        UserAllDetailsDTO user = userDAO.getUserAllInfoById(id).orElseThrow(()->
                new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
        if (userDetailUpdateDTO.getName()!=null){
            userDetailUpdateDTO.setName(userDetailUpdateDTO.getName());
        }else {
            userDetailUpdateDTO.setName(user.getName());
        }
        if (userDetailUpdateDTO.getSurname()!=null){
            userDetailUpdateDTO.setSurname(userDetailUpdateDTO.getSurname());
        }else {
            userDetailUpdateDTO.setSurname(user.getSurname());
        }
        if (userDetailUpdateDTO.getAddress()!=null){
            userDetailUpdateDTO.setAddress(userDetailUpdateDTO.getAddress());
        }else {
            userDetailUpdateDTO.setAddress(user.getAddress());
        }
        if (userDetailUpdateDTO.getPhone()!=null){
            userDetailUpdateDTO.setPhone(userDetailUpdateDTO.getPhone());
        }else {
            userDetailUpdateDTO.setPhone(user.getPhone());
        }
        userDAO.updateUserDetails(id, userDetailUpdateDTO);
    }

    @Override
    public List<UserDTO> findUsers(UserForGetDTO userForGetDTO) {
        List<UserDTO> userDTOs = userDAO.findUsers(userForGetDTO)
                .stream().map(user -> new UserDTO(user.getLogin(), user.getEmail()))
                .collect(Collectors.toList());
        if (userDTOs.size()!=0){
            return userDTOs;
        }else{
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode());
        }
    }

    @Override
    public List<UserAllInfoDTO> findUsersAllInfo(UserForGetDTO userForGetDTO) {
        List<UserAllInfoDTO> userAllInfoDTOS = userDAO.findUsers(userForGetDTO)
                .stream().map(user -> new UserAllInfoDTO(user.getLogin(), user.getEmail(), user.getName(),
                        user.getSurname(), user.getAddress(), user.getPhone()))
                .collect(Collectors.toList());
        if (userAllInfoDTOS.size() != 0) {
            return userAllInfoDTOS;
        } else {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode());
        }
    }

}

