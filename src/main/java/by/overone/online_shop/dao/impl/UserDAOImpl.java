package by.overone.online_shop.dao.impl;

import by.overone.online_shop.dao.UserDAO;
import by.overone.online_shop.dto.UserRegistretionDTO;
import by.overone.online_shop.dto.UserUpdateDTO;
import by.overone.online_shop.model.Role;
import by.overone.online_shop.model.Status;
import by.overone.online_shop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

    private final static String GET_ALL_USERS_QUERY = "SELECT * FROM users";
    private final static String GET_ALL_USER_BY_STATUS_QUERY = "SELECT * FROM users WHERE status=?";
    private final static String GET_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    private final static String GET_USER_BY_LOGIN_QUERY = "SELECT * FROM users WHERE login=?";
    private final static String GET_USER_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email=?";
    private final static String ADD_USER_QUERY = "INSERT INTO users VALUES(0,?,?,?,?,?)";
    private final static String ADD_USER_DETAILS_ID_QUERY = "INSERT INTO users_details(users_id) VALUE(?)";
    private final static String ADD_USER_DETAILS_QUERY = "UPDATE users_details SET name=?, " +
            "surname=?, address=?, phone=? WHERE users_id=?";
    private final static String DELETE_USER_QUERY = "UPDATE users SET status='INACTIVE' WHERE id=?";
    private final static String GET_USER_ALL_DATA_QUERY = "SELECT*FROM users JOIN users_details ON " +
            "id=users_id WHERE id=?";
    private final static String UPDATE_USER_QUERY = "UPDATE users SET login=COALESCE(?, login)," +
            " password=COALESCE(?, password), email=COALESCE(?, email, last_update_date=DEFAULT WHERE id=?)";
    private final static String UPDATE = "UPDATE users SET login=?, password=?, email=? WHERE id=?";


    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query(GET_ALL_USERS_QUERY, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public List<User> getAllUserByStatus(String status) {
        return jdbcTemplate.query(GET_ALL_USER_BY_STATUS_QUERY,
                new Object[]{status},
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getUserById(long id) {
        return jdbcTemplate.query(GET_USER_BY_ID_QUERY,
                new Object[]{id},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    @Override
    public User getUserByLogin(String login) {
        return jdbcTemplate.query(GET_USER_BY_LOGIN_QUERY,
                new Object[]{login},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return jdbcTemplate.query(GET_USER_BY_EMAIL_QUERY,
                new Object[]{email},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    @Override
    public void addUser(User user) {
        jdbcTemplate.update(ADD_USER_QUERY,
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getRole(),
                user.getStatus());
    }

    @Override
    public void deleteUser(long id) {
        jdbcTemplate.update(DELETE_USER_QUERY, id);

    }

    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO) {
        jdbcTemplate.update(UPDATE, userUpdateDTO.getLogin(), userUpdateDTO.getPassword(),
                userUpdateDTO.getEmail(), userUpdateDTO.getId());
    }
}
