/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truong.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author macosx
 */
public class Record {
    int RecordID;
    boolean RecordType;
    String RecordDate;
    String StaffID;
    String Reason;
    int type;
    String suaType;
    String Staffname;
    String Departid;
    String photo;
    String email;
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    

    public String getDepartid() {
        return Departid;
    }

    public void setDepartid(String Departid) {
        this.Departid = Departid;
    }

    public String getStaffname() {
        return Staffname;
    }

    public void setStaffname(String Staffname) {
        this.Staffname = Staffname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    
    
    public Record() {
    }

    public Record(int RecordID, boolean RecordType, String RecordDate, String StaffID, String Reason) {
        this.RecordID = RecordID;
        this.RecordType = RecordType;
        this.RecordDate = RecordDate;
        this.StaffID = StaffID;
        this.Reason = Reason;
    }

    public int getRecordID() {
        return RecordID;
    }

    public void setRecordID(int RecordID) {
        this.RecordID = RecordID;
    }

    public boolean isRecordType() {
        return RecordType;
    }

    public int getType() {
        if(RecordType){
            return 0;
        }else{
            return 1;
        }
    }
    
    public void setRecordType(boolean RecordType) {
        this.RecordType = RecordType;
    }

    public String getRecordDate() {
        return RecordDate;
    }

    public void setRecordDate(String RecordDate) {
        this.RecordDate = RecordDate;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getSuaType() {
        if (!this.RecordType) {
            return "Phat(-)";
        } else {
            return "Thuong(+)";
        }
    }
    
    public SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat DATE_FORMATER1 = new SimpleDateFormat("dd-MM-yyyy");
    
    public String FixNgay(String date){
        try{
            Date newdate=DATE_FORMATER.parse(date);
            return DATE_FORMATER1.format(newdate);
        }catch(Exception e){
            System.out.println(e);
            return "ngayloi";
        }
    }
    
    
}
