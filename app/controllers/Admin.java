package controllers;

import models.Staff;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

/**
 * Created by luhaoran on 7/8/17.
 */
@With(Secure.class)
public class Admin extends Controller {

    @Before
    static  void setConnectedStaff() {
        if (Security.isConnected()) {
            Staff staff = Staff.find("byUsername", Security.connected()).first();
            renderArgs.put("staff", staff);
        }
    }

    public static void index() {
        render();
    }
}
