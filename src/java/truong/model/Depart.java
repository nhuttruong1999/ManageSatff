/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truong.model;

import java.io.Serializable;

/**
 *
 * @author macosx
 */
public class Depart implements Serializable{
    String Departid;
    String Departname;
    String Departimg;
    boolean DeleteStatus;
    String Delete;
    int gg;
    

    public Depart() {
    }

    public Depart(String Departid, String Departname, String Departimg, boolean DeleteStatus) {
        this.Departid = Departid;
        this.Departname = Departname;
        this.Departimg = Departimg;
        this.DeleteStatus = DeleteStatus;
    }

    public String getDepartid() {
        return Departid;
    }

    public void setDepartid(String Departid) {
        this.Departid = Departid;
    }

    public String getDepartname() {
        return Departname;
    }

    public void setDepartname(String Departname) {
        this.Departname = Departname;
    }

    public String getDepartimg() {
        return Departimg;
    }

    public void setDepartimg(String Departimg) {
        this.Departimg = Departimg;
    }

    public boolean isDeleteStatus() {
        return DeleteStatus;
    }

    public int getGg() {
        if (DeleteStatus) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public void setDeleteStatus(boolean DeleteStatus) {
        this.DeleteStatus = DeleteStatus;
    }

    public String getDelete() {
        if (!this.DeleteStatus) {
            return "activated";
        } else {
            return "inactivated";
        }
    }

    public void setDelete(String Delete) {
        this.Delete = Delete;
    }
    
}
