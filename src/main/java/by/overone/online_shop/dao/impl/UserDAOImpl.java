package by.overone.online_shop.dao.impl;

import by.overone.online_shop.dao.UserDAO;
import by.overone.online_shop.dto.*;
import by.overone.online_shop.model.User;
import by.overone.online_shop.model.UserDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

    private final static String GET_ALL_USERS_QUERY = "SELECT * FROM users";
    private final static String GET_ALL_USERS_BY_STATUS_QUERY = "SELECT * FROM users WHERE status=?";
    private final static String GET_ALL_USERS_BY_NAME_QUERY = "SELECT * FROM users WHERE name=?";
    private final static String GET_ALL_USERS_BY_SURNAME_QUERY = "SELECT * FROM users WHERE surname=?";
    private final static String GET_ALL_USERS_BY_FULNAME_QUERY = "SELECT id, login, email, role," +
            " status FROM users JOIN users_details ON id=users_id WHERE name=? AND surname=?";
    private final static String GET_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    private final static String GET_USER_DETAIL_BY_ID_QUERY = "SELECT * FROM users_details WHERE users_id=?";
    private final static String GET_USER_BY_LOGIN_QUERY = "SELECT * FROM users WHERE login=?";
    private final static String GET_USER_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email=?";
    private final static String ADD_USER_QUERY = "INSERT INTO users (login, password, email, role, status)" +
            " VALUES(:login, :password, :email, :role, :status)";
    //    private final static String ADD_USER_QUERY = "INSERT INTO users VALUES(0,?,?,?,?,?)";
    private final static String ADD_USER_DETAILS_ID_QUERY = "INSERT INTO users_details(users_id) VALUE(?)";
    private final static String ADD_USER_DETAILS_QUERY = "UPDATE users_details SET name=?, " +
            "surname=?, address=?, phone=? WHERE users_id=?";
    private final static String DELETE_USER_QUERY = "UPDATE users SET status='INACTIVE' WHERE id=?";
    private final static String GET_USER_ALL_DATA_BY_ID_QUERY = "SELECT*FROM users JOIN users_details ON " +
            "id=users_id WHERE id=?";
    private final static String UPDATE_USER_QUERY = "UPDATE users SET login=?, password=?," +
            " email=?, role=?, status=?  WHERE id=?";
    private final static String UPDATE_USER_DETAILS_QUERY = "UPDATE users_details SET name=?, " +
            "surname=?, address=?, phone=? WHERE users_id=?";
//    private final static String UPDATE = "UPDATE users JOIN users_details ON id=users_id SET login=?, password=?, email=?," +
//            " name=?, surname=, address=?, phone=? WHERE id=?";


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query(GET_ALL_USERS_QUERY, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

//    @Override
//    public List<User> findUser(String name, String surname, String status) {
//        if (name != null) {
//            List<User> users = jdbcTemplate.query(GET_ALL_USERS_BY_NAME_QUERY, new BeanPropertyRowMapper<>(User.class));
//            return users;
//        }
//        if (surname != null) {
//            List<User> users = jdbcTemplate.query(GET_ALL_USERS_BY_SURNAME_QUERY , new BeanPropertyRowMapper<>(User.class));
//            return users;
//        }
////        if (name != null && surname != null) {
////            List<User> users = jdbcTemplate.query(GET_ALL_USERS_BY_FULNAME_QUERY, new BeanPropertyRowMapper<>(User.class));
////            return users;
////        }
//        if (status != null) {
//            System.out.println(3);
//            List<User> users = jdbcTemplate.query(GET_ALL_USERS_BY_STATUS_QUERY, new BeanPropertyRowMapper<>(User.class));
//            return users;
//        }
//        if (name == null && surname == null && status == null){
//            List<User> users = jdbcTemplate.query(GET_ALL_USERS_BY_FULNAME_QUERY, new BeanPropertyRowMapper<>(User.class));
//            return users;
//        }
//
//            return null;
//    }

    @Override
    public List<User> getAllUserByStatus(String status) {
        return jdbcTemplate.query(GET_ALL_USERS_BY_STATUS_QUERY,
                new Object[]{status},
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> getUserByFullname(String name, String surname) {
        return jdbcTemplate.query(GET_ALL_USERS_BY_FULNAME_QUERY,
                new Object[]{name, surname},
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public Optional<User> getUserById(long id) {
        return jdbcTemplate.query(GET_USER_BY_ID_QUERY,
                new Object[]{id},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }

    @Override
    public Optional<UserDetail> getUserDetailByUserId(long users_id) {
        return jdbcTemplate.query(GET_USER_DETAIL_BY_ID_QUERY,
                new Object[]{users_id},
                new BeanPropertyRowMapper<>(UserDetail.class)).stream().findAny();
    }

    @Override
    public UserAllDetailsDTO getUserAllDetailsById(long id) {
        return jdbcTemplate.query(GET_USER_ALL_DATA_BY_ID_QUERY,
                new Object[]{id},
                new BeanPropertyRowMapper<>(UserAllDetailsDTO.class)).stream().findAny().orElse(null);
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
    @Transactional
    public void addUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("role", user.getRole())
                .addValue("status", user.getStatus());
        namedParameterJdbcTemplate.update(ADD_USER_QUERY, sqlParameterSource, keyHolder, new String[]{"id"});
        user.setId(keyHolder.getKey().longValue());
        jdbcTemplate.update(ADD_USER_DETAILS_ID_QUERY, user.getId());
    }

    @Override
    public void deleteUser(long id) {
        jdbcTemplate.update(DELETE_USER_QUERY, id);

    }

    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO) {
        System.out.println(userUpdateDTO.toString());
        jdbcTemplate.update(UPDATE_USER_QUERY, userUpdateDTO.getLogin(), userUpdateDTO.getPassword(),
                userUpdateDTO.getEmail(), userUpdateDTO.getRole(), userUpdateDTO.getStatus(), userUpdateDTO.getId());
    }

    @Override
    public void updateUserDetails(UserDetailUpdateDTO userDetailUpdateDTO) {
        System.out.println(userDetailUpdateDTO.toString());
        jdbcTemplate.update(UPDATE_USER_DETAILS_QUERY, userDetailUpdateDTO.getName(), userDetailUpdateDTO.getSurname(),
                userDetailUpdateDTO.getAddress(), userDetailUpdateDTO.getPhone(), userDetailUpdateDTO.getUsers_id());
    }
}
