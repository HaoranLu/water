package controllers;

import models.Staff;
import models.User;
import play.*;
import play.mvc.Controller;
import play.mvc.With;

import java.util.Date;

/**
 * Created by luhaoran on 7/6/17.
 */
@With(Secure.class)
public class Users extends CRUD {

    public static void finishChange(long userid, long changerid) {
        User user = User.findById(userid);
        user.lastChange = new Date();
        Staff s = Staff.findById(changerid);
        user.lastChanger = s;

        user.save();
        Application.index();
    }




}
