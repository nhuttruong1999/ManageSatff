/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truong.model;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author macosx
 */
@Repository
public class DepartDAO {
    @Autowired
    protected JdbcTemplate jdbc;
    
    public Depart getDepartid(Serializable Departid) {
        String sql = "SELECT * FROM Depart WHERE DepartID=?";
        return jdbc.queryForObject(sql, getRowMapper(), Departid);
    }
    
    public List<Depart> getAll() {
        String sql = "SELECT * FROM Depart ";
        return getBySql(sql); // trả về danh sách các tài khoản
    }

    
    protected List<Depart> getBySql(String sql) {
        return jdbc.query(sql, getRowMapper());
    }

    
    private RowMapper<Depart> getRowMapper() {
        return new BeanPropertyRowMapper<Depart>(Depart.class);
    }
    
    public void insertDepart(Depart dp) {
        String sql = "INSERT INTO Depart VALUES (?,?,?,?)";
        jdbc.update(sql, dp.getDepartid(),dp.getDepartname(),dp.getDepartimg(),dp.isDeleteStatus());
    }
    
    public void updateDepart(Depart dp) {
        String sql = "UPDATE Depart SET Departname=?,Departimg=?, DeleteStatus=? WHERE DepartID=?";
        jdbc.update(sql,dp.getDepartname(),dp.getDepartimg(),dp.isDeleteStatus(),dp.getDepartid());
    }
    
    public void deleteDepart(String Depart) {
        String sql = "DELETE FROM Depart WHERE DepartID=?";
        jdbc.update(sql, Depart);
    } 
    
    public List<Depart> getByDepartID(String DepartID) {
         String sql = "SELECT * FROM Depart WHERE DepartID LIKE ?";
        return jdbc.query(sql, getRowMapper(), "%" + DepartID + "%");
    } 
}
