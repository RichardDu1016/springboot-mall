package com.cloudpb.springmall.rowmapper;

import com.cloudpb.springmall.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    // 對應到資料庫的欄位名稱
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreateDate(rs.getDate("created_date"));
        user.setLastModifiedDate(rs.getDate("last_modified_date"));

        return user;
    }
}
