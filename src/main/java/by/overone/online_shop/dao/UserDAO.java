package by.overone.online_shop.dao;

import by.overone.online_shop.dto.*;
import by.overone.online_shop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    Optional<UserAllDetailsDTO> getUserAllInfoById(long id);
    List<UserAllDetailsDTO> getUserAllInfo(UserForGetDTO userForGetDTO);
    void addUser(User user);
    void updateUser(long id, UserAllDetailsDTO user);
    void updateUserDetails(long userId, UserAllDetailsDTO user);
    void deleteUser(long id);

}
