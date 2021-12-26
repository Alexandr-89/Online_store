package by.overone.online_shop.dao.mapper;

import by.overone.online_shop.constant.UserConstant;
import by.overone.online_shop.model.Role;
import by.overone.online_shop.model.Status;
import by.overone.online_shop.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(UserConstant.ID));
        user.setLogin(rs.getString(UserConstant.LOGIN));
        user.setPassword(rs.getString(UserConstant.PASSWORD));
        user.setEmail(rs.getString(UserConstant.EMAIL));
        user.setRole(Role.valueOf(rs.getString(UserConstant.ROLE).toUpperCase(Locale.ROOT)));
        user.setStatus(Status.valueOf(rs.getString(UserConstant.STATUS).toUpperCase(Locale.ROOT)));
        return user;
    }
}
