package by.overone.online_shop.service;

import by.overone.online_shop.dto.*;

import java.util.List;

public interface UserService {

//    List<UserDTO> getAllUsers();
//    List<UserDTO> findUser(String name, String surname, String status);
//    List<UserDTO> getAllUsersByStatus(String status);
//    List<UserDTO>getUserByFullname(String name, String surname);
    void addUser(UserRegistrationDTO userRegistrationDTO);
    UserDTO getUserById(long id);
//    UserDetailDTO getUserDetailById(long users_id);
//    UserAllDetailsDTO getUserAllDetailsById(long id);
//    UserDTO getUserByLogin(String login);
//    UserDTO getUserByEmail(String email);
    void deleteUser(long id);
    void userUpdate(long id, UserUpdateDTO userUpdateDTO);
    void userDetailUpdate(long id, UserDetailUpdateDTO userDetailUpdateDTO);
    List<UserDTO> findUsers(UserForGetDTO userForGetDTO);
    List<UserAllInfoDTO> findUsersAllInfo(UserForGetDTO userForGetDTO);

}
