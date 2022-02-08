package by.overone.online_shop.dao.impl;

import by.overone.online_shop.dao.UserDAO;
import by.overone.online_shop.dto.*;
import by.overone.online_shop.model.Role;
import by.overone.online_shop.model.Status;
import by.overone.online_shop.model.User;
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


    private final static String GET_USER_BY_ID_QUERY = "SELECT * FROM users JOIN users_details ON id=users_id WHERE id=?";

    private final static String ADD_USER_QUERY = "INSERT INTO users (login, password, email, role, status)" +
            " VALUES(:login, :password, :email, :role, :status)";

    private final static String ADD_USER_DETAILS_ID_QUERY = "INSERT INTO users_details(users_id) VALUE(?)";

    private final static String DELETE_USER_QUERY = "UPDATE users SET status='INACTIVE' WHERE id=?";

    private final static String UPDATE_USER_QUERY = "UPDATE users SET login=?, password=?," +
            " email=?, role=?, status=?  WHERE id=?";

    private final static String UPDATE_USER_DETAILS_QUERY = "UPDATE users_details SET name=?, " +
            "surname=?, address=?, phone=? WHERE users_id=?";



    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Optional<UserAllDetailsDTO> getUserAllInfoById(long id) {
        return jdbcTemplate.query(GET_USER_BY_ID_QUERY,
                new BeanPropertyRowMapper<>(UserAllDetailsDTO.class), id).stream().findAny();
    }


    @Override
    public List<UserAllDetailsDTO> getUserAllInfo(UserForGetDTO user) {

        String sql = "SELECT * FROM users JOIN users_details ON id=users_id";

        if (user.getLogin() == null && user.getEmail() == null && user.getRole() == null &&
                user.getStatus() == null && user.getName() == null && user.getSurname() != null &&
                user.getAddress() != null && user.getPhone() != null) {
        }
        if (user.getLogin() != null) {
            sql = sql + " WHERE login = '" + user.getLogin() + "'";
            System.out.println(sql);
        }
        if (user.getEmail() != null) {
            sql = sql + " WHERE email = '" + user.getEmail() + "'";
        }
        if (user.getRole() != null) {
            sql = sql + " WHERE role = '" + user.getRole() + "'";
        }
        if (user.getStatus() != null) {
            sql = sql + " WHERE status = '" + user.getStatus() + "'";
        }
        if (user.getName() != null && user.getSurname() == null){
            sql = sql + " WHERE name = '" + user.getName() + "'";
        }
        if (user.getName() == null && user.getSurname() != null){
            sql = sql + " WHERE surname = '" + user.getSurname() + "'";
        }
        if (user.getName() != null && user.getSurname() != null){
            sql = sql + " WHERE name = '" + user.getName() + "' AND surname = '" + user.getSurname() +"'" ;
        }
        if (user.getAddress() != null){
            sql = sql + " WHERE address = '" + user.getAddress() + "'";
        }
        if (user.getPhone() != null){
            sql = sql + " WHERE phone = '" + user.getPhone() + "'";
        }
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserAllDetailsDTO.class), new Object[]{});
    }


    @Override
    @Transactional
    public void addUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("role", Role.CUSTOMER.toString())
                .addValue("status", Status.ACTIVE.toString());
        namedParameterJdbcTemplate.update(ADD_USER_QUERY, sqlParameterSource, keyHolder, new String[]{"id"});
        user.setId(keyHolder.getKey().longValue());
        jdbcTemplate.update(ADD_USER_DETAILS_ID_QUERY, user.getId());
    }


    @Override
    public void updateUser(long id, UserAllDetailsDTO user) {
        jdbcTemplate.update(UPDATE_USER_QUERY, user.getLogin(), user.getPassword(),
                user.getEmail(), user.getRole(), user.getStatus(), id);
    }


    @Override
    public void updateUserDetails(long userId, UserAllDetailsDTO user) {
        jdbcTemplate.update(UPDATE_USER_DETAILS_QUERY, user.getName(), user.getSurname(),
                user.getAddress(), user.getPhone(), userId);
    }


    @Override
    public void deleteUser(long id) {
        jdbcTemplate.update(DELETE_USER_QUERY, id);
    }

}

