package by.overone.online_shop.dao;

import by.overone.online_shop.dto.UserRegistretionDTO;
import by.overone.online_shop.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();
    User getUserById(long id);
    void addUser(UserRegistretionDTO userRegistretionDTO);

}
