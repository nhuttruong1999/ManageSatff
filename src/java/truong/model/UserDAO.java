/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truong.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 *
 * @author macosx
 */
@Repository
public class UserDAO {
      
    @Autowired
    protected JdbcTemplate jdbc;
    
    public User getByUsername(Serializable Username) {
        String sql = "SELECT * FROM user WHERE Username=?";
        return jdbc.queryForObject(sql, getRowMapper(), Username);
    }
    
    public List<User> getAll() {
        String sql = "SELECT * FROM user";
        return getBySql(sql); // trả về danh sách các tài khoản
    }

    
    protected List<User> getBySql(String sql) {
        return jdbc.query(sql, getRowMapper());
    }

    
    private RowMapper<User> getRowMapper() {
        return new BeanPropertyRowMapper<User>(User.class);
    }
    
    public void insert(User acc) {
        String sql = "INSERT INTO user VALUES (?,?,?,?)";
        jdbc.update(sql, acc.getUsername(),acc.getPassword(),acc.getFullname(),acc.getImg());
    }
    
    public void update(User acc) {
        String sql = "UPDATE user SET password=? ,fullname=?, img=? WHERE username=?";
        jdbc.update(sql, acc.getPassword(),acc.getFullname(),acc.getImg(), acc.getUsername());
    }
    
    public void delete(String username) {
        String sql = "DELETE FROM user WHERE username=?";
        jdbc.update(sql, username);
    } 
    
    public List<User> getByName(String username) {
         String sql = "SELECT * FROM user WHERE username LIKE ?";
        return jdbc.query(sql, getRowMapper(), "%" + username + "%");
    } 
   
}
