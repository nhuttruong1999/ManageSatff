/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truong.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author macosx
 */
public class Staff implements Serializable{
    String Staffid;
    String Staffname;
    boolean gender;
    String birthday;
    String photo;
    String email;
    String phone;
    float salary;
    String notes;
    String departid;
    boolean deleted;
    String sex;
    int suagt;
    int suatt;

    public Staff() {
    }

    public Staff(String Staffid, String Staffname, boolean gender, String birthday, String photo, String email, String phone, float salary, String notes, String departid, boolean deleted, String sex) {
        this.Staffid = Staffid;
        this.Staffname = Staffname;
        this.gender = gender;
        this.birthday = birthday;
        this.photo = photo;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.notes = notes;
        this.departid = departid;
        this.deleted = deleted;
        this.sex = sex;
    }

    public String getStaffid() {
        return Staffid;
    }

    public void setStaffid(String Staffid) {
        this.Staffid = Staffid;
    }

    public String getStaffname() {
        return Staffname;
    }

    public void setStaffname(String Staffname) {
        this.Staffname = Staffname;
    }

    public boolean isGender() {
        return gender;
    }

    public int getSuagt() {
        if (gender) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public int getSuatt() {
        if (deleted) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getSex() {
        if (!this.gender) {
            return "Male";
        } else {
            return "Female";
        }
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDeleteStatus(){
        if (!this.deleted) {
            return "ON";
        } else {
            return "OFF";
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
