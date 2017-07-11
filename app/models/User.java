package models;


import play.data.validation.MaxSize;
import play.data.validation.Phone;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by luhaoran on 7/5/17.
 */

@Entity
public class User extends Model {

    @Required
    @MaxSize(50)
    public String name;

    @Required
    @Phone
    public String phone;


    @MaxSize(200)
    public String address;

    @Temporal(TemporalType.DATE)
    public Date installDate;

    @Temporal(TemporalType.DATE)
    public Date lastChange; // latest filter change date

    @ManyToOne
    public Staff lastChanger;

    @Lob
    @MaxSize(1000)
    public String reason; // why wont install

    public String appointment;

    @ManyToOne
    public Staff installer;

    @ManyToOne
    public Staff director;

    public boolean installed;

    @Lob
    @MaxSize(50)
    public String note;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String toString() {
        String temp = this.installed ? "已安装" : " 未安装";
        return temp + "  " + this.name;
    }
}
