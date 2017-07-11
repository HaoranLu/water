package models;

import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by luhaoran on 7/5/17.
 */

@Entity
public class Staff extends Model {

    @Required
    @MaxSize(15)
    @MinSize(4)
    @Match(value="^\\w*$", message="Not a valid username")
    public String username;

    @Required
    @MaxSize(15)
    @MinSize(5)
    public String password;

    @Required
    @MaxSize(100)
    public String name;
    public boolean isAdmin;

    public Staff(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public static Staff connect(String username, String password) {
        return find("byUsernameAndPassword", username, password).first();
    }
}
