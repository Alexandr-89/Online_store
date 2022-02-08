package by.overone.online_shop.service;

import by.overone.online_shop.dto.*;

import java.util.List;

public interface UserService {


    UserDTO getUserById(Long id);
    List<UserDTO> findUsers(UserForGetDTO userForGetDTO);
    List<UserAllInfoDTO> findUsersAllInfo(UserForGetDTO userForGetDTO);
    void addUser(UserRegistrationDTO userRegistrationDTO);
    void userUpdate(Long id, UserUpdateDTO userUpdateDTO);
    void userDetailUpdate(Long id, UserDetailUpdateDTO userDetailUpdateDTO);
    void deleteUser(Long id);

}
