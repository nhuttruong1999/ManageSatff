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
public class RecordDAO {
    @Autowired
    protected JdbcTemplate jdbc;
    
    public Record getByRecordID(Serializable recordid) {
        String sql = "SELECT * FROM Record join Staff on Record.StaffID=Staff.StaffID WHERE RecordID=?";
        return jdbc.queryForObject(sql, getRowMapper(), recordid);
    }
    
    public List<Record> getAll() {
        String sql = "SELECT * FROM Record ";
        return getBySql(sql); // trả về danh sách các tài khoản
    }
    
    public List<Record> getTOP10(){
        String sql="SELECT count(*),Staff.StaffID, Staff.StaffName, Photo, Staff.DepartID FROM Staff join Record on Staff.StaffID=Record.StaffID join Depart\n" +
"on Depart.DepartID = Staff.DepartID where Record.RecordType=1 group by Staff.StaffID order by count(*) desc limit 10";
        return getBySql(sql);
    }

    
    protected List<Record> getBySql(String sql) {
        return jdbc.query(sql, getRowMapper());
    }

    
    private RowMapper<Record> getRowMapper() {
        return new BeanPropertyRowMapper<Record>(Record.class);
    }
    
    public void insertRecord(Record rc) {  
        String sql = "INSERT INTO Record VALUES (default,?,?,?,?) ";
        jdbc.update(sql, rc.isRecordType(),rc.getRecordDate(),rc.getStaffID(),rc.getReason());
                         
    }
    
    public void updateRecord(Record rc) {
        String sql = "UPDATE Record SET RecordType=?, RecordDate=?, StaffID=?, Reason=? WHERE RecordID=? ";
        jdbc.update(sql,rc.getType(),rc.getRecordDate(),rc.getStaffID(),rc.getReason(),rc.getRecordID());
    }
    
    public void deleteRecord(int recordid) {
        String sql = "DELETE FROM Record WHERE Recordid=?";
        jdbc.update(sql, recordid);
    } 
    
    public List<Record> getsearchStaffID(String staffid) {
         String sql = "SELECT * FROM Record WHERE StaffID LIKE ?";
        return jdbc.query(sql, getRowMapper(), "%" + staffid + "%");
    } 
    
    
}
