package by.overone.online_shop.dao;

import by.overone.online_shop.dto.*;
import by.overone.online_shop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    Optional<UserAllDetailsDTO> getUserAllInfoById(Long id);
    List<UserAllDetailsDTO> getUserAllInfo(UserForGetDTO userForGetDTO);
    void addUser(User user);
    void updateUser(Long id, UserAllDetailsDTO user);
    void updateUserDetails(Long userId, UserAllDetailsDTO user);
    void deleteUser(Long id);

}
