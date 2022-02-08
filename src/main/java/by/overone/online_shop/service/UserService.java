package by.overone.online_shop.service;

import by.overone.online_shop.dto.*;

import java.util.List;

public interface UserService {


    UserDTO getUserById(long id);
    List<UserDTO> findUsers(UserForGetDTO userForGetDTO);
    List<UserAllInfoDTO> findUsersAllInfo(UserForGetDTO userForGetDTO);
    void addUser(UserRegistrationDTO userRegistrationDTO);
    void userUpdate(long id, UserUpdateDTO userUpdateDTO);
    void userDetailUpdate(long id, UserDetailUpdateDTO userDetailUpdateDTO);
    void deleteUser(long id);

}
