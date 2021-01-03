/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truong.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class StaffDAO {
    @Autowired
    protected JdbcTemplate jdbc;
    
    public Staff getByStaffid(Serializable Staffid) {
        String sql = "SELECT * FROM Staff WHERE Staffid=?";
        return jdbc.queryForObject(sql, getRowMapper(), Staffid);
    }
    
    public List<Staff> getAll() {
        String sql = "SELECT * FROM Staff ";
        return getBySql(sql); // trả về danh sách các tài khoản
    }

    
    protected List<Staff> getBySql(String sql) {
        return jdbc.query(sql, getRowMapper());
    }

    
    private RowMapper<Staff> getRowMapper() {
        return new BeanPropertyRowMapper<Staff>(Staff.class);
    }
    
    public void insertStaff(Staff st) {  
        String sql = "INSERT INTO Staff VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
        jdbc.update(sql, st.getStaffid(),st.getStaffname(),st.isGender(),st.getBirthday(),
                         st.getPhoto(),st.getEmail(),st.getPhone(),st.getSalary(),st.getNotes(),
                         st.getDepartid(),st.isDeleted());
    }
    
    public void updateStaff(Staff st) {
        String sql = "UPDATE Staff SET Staffname=? ,Gender=?, Birthday=?, Photo=?, Email=?, Phone=?, Salary=?, Notes=? ,Departid=? , Deleted=? WHERE StaffID=? ";
        jdbc.update(sql, st.getStaffname(),st.isGender(),st.getBirthday(),
                         st.getPhoto(),st.getEmail(),st.getPhone(),st.getSalary(),st.getNotes(),
                         st.getDepartid(),st.isDeleted(),st.getStaffid());
    }
    
    public void deleteStaff(String Staffid) {
        String sql = "DELETE FROM Staff WHERE Staffid=?";
        jdbc.update(sql, Staffid);
    } 
    
    public List<Staff> getByStaffname(String Staffid) {
         String sql = "SELECT * FROM Staff WHERE Staffid LIKE ?";
        return jdbc.query(sql, getRowMapper(), "%" + Staffid + "%");
    } 
}
