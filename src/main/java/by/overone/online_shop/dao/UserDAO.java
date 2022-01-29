package by.overone.online_shop.dao;

import by.overone.online_shop.dto.*;
import by.overone.online_shop.model.User;
import by.overone.online_shop.model.UserDetail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> getAllUsers();
//    List<User> findUser(String name, String surname, String status);
    List<User> getAllUserByStatus(String status);
    List<User> getUserByFullname(String name, String surname);
    Optional<User> getUserById(long id);
    UserDetail getUserDetailByUserId(long users_id);
    UserAllDetailsDTO getUserAllDetailsById(long id);
    User getUserByLogin(String login);
    User getUserByEmail(String email);
    void addUser(User user);
    void deleteUser(long id);
    void updateUser(UserUpdateDTO userUpdateDTO);
    void updateUserDetails(UserDetailUpdateDTO userDetailUpdateDTO);

}
