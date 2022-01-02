package by.overone.online_shop.dao;

import by.overone.online_shop.dto.UserAllDetailsDTO;
import by.overone.online_shop.dto.UserRegistretionDTO;
import by.overone.online_shop.dto.UserUpdateDTO;
import by.overone.online_shop.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();
    List<User> getAllUserByStatus(String status);
    User getUserById(long id);
    UserAllDetailsDTO getUserAllDetailsById(long id);
    User getUserByLogin(String login);
    User getUserByEmail(String email);
    void addUser(User user);
    void deleteUser(long id);
    void updateUser(UserUpdateDTO userUpdateDTO);

}
