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




    @Override
    public UserDTO getUserById(long id) {
        if (id>=1){
            UserDTO userDTOs = new UserDTO();
            UserAllDetailsDTO user = userDAO.getUserAllInfoById(id)
                    .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode()));
            userDTOs.setLogin(user.getLogin());
            userDTOs.setEmail(user.getEmail());
            return userDTOs;
        }else {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode());
        }
    }


    @Override
    public List<UserDTO> findUsers(UserForGetDTO userForGetDTO) {
        List<UserDTO> userDTOs = userDAO.getUserAllInfo(userForGetDTO)
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
        List<UserAllInfoDTO> userAllInfoDTOS = userDAO.getUserAllInfo(userForGetDTO)
                .stream().map(user -> new UserAllInfoDTO(user.getLogin(), user.getEmail(), user.getName(),
                        user.getSurname(), user.getAddress(), user.getPhone()))
                .collect(Collectors.toList());
        if (userAllInfoDTOS.size() != 0) {
            return userAllInfoDTOS;
        } else {
            throw new EntityNotFoundException(ExceptionCode.NOT_EXISTING_USER.getErrorCode());
        }
    }


    @Override
    public void addUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setLogin(userRegistrationDTO.getLogin());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setRole(Role.CUSTOMER);
        user.setStatus(Status.ACTIVE);
        userDAO.addUser(user);
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
            user.setName(userDetailUpdateDTO.getName());
        }
        if (userDetailUpdateDTO.getSurname()!=null){
            user.setSurname(userDetailUpdateDTO.getSurname());
        }
        if (userDetailUpdateDTO.getAddress()!=null){
            user.setAddress(userDetailUpdateDTO.getAddress());
        }
        if (userDetailUpdateDTO.getPhone()!=null){
            user.setPhone(userDetailUpdateDTO.getPhone());
        }
        userDAO.updateUserDetails(id, user);
    }


    @Override
    public void deleteUser(long id) {
        getUserById(id);
        userDAO.deleteUser(id);
    }

}

