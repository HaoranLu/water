package controllers;

import models.Staff;
import models.User;

/**
 * Created by luhaoran on 7/8/17.
 */
public class Security extends Secure.Security{

    public static boolean authenticate(String username, String password) {
        return Staff.connect(username, password) != null;
    }

    static void onDisconnected() {
        Application.index();
    }

    static void onAuthenticated() {
        Application.index();
    }

    static boolean check(String profile) {
        if ("admin".equals(profile)) {
            return Staff.find("byUsername", connected()).<Staff>first().isAdmin;
        }
        return false;
    }
}
