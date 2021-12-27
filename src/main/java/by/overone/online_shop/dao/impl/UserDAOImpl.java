package by.overone.online_shop.dao.impl;

import by.overone.online_shop.dao.UserDAO;
import by.overone.online_shop.dao.mapper.UserRowMapper;
import by.overone.online_shop.dto.UserRegistretionDTO;
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


    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query(GET_ALL_USERS_QUERY, new UserRowMapper());
        return users;
    }

    @Override
    public List<User> getAllUserByStatus(String status) {
        return jdbcTemplate.query(GET_ALL_USER_BY_STATUS_QUERY,
                new Object[]{status},
                new UserRowMapper());
    }

    @Override
    public User getUserById(long id) {
        return jdbcTemplate.query(GET_USER_BY_ID_QUERY,
                new Object[]{id},
                new UserRowMapper()).stream().findAny().orElse(null);
    }

    @Override
    public User getUserByLogin(String login) {
        return jdbcTemplate.query(GET_USER_BY_LOGIN_QUERY,
                new Object[]{login},
                new UserRowMapper()).stream().findAny().orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return jdbcTemplate.query(GET_USER_BY_EMAIL_QUERY,
                new Object[]{email},
                new UserRowMapper()).stream().findAny().orElse(null);
    }

    @Override
    public void addUser(UserRegistretionDTO userRegistretionDTO) {
        jdbcTemplate.update(ADD_USER_QUERY,
                userRegistretionDTO.getLogin(),
                userRegistretionDTO.getPassword(),
                userRegistretionDTO.getEmail(),
                Role.CUSTOMER.toString(),
                Status.ACTIVE.toString());
    }
}
