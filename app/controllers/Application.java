package controllers;

import play.*;
import play.db.jpa.JPA;
import play.mvc.*;

import java.util.*;

import models.*;


public class Application extends Controller {

    @Before
    static void putStaff() {
        if (Security.isConnected()) {
            Staff staff = Staff.find("byUsername", Security.connected()).first();
            renderArgs.put("staff", staff);
        }
    }


    public static void index() {
        Date today = new Date();
        List<User> changeUsers = filterChange(today);
        render(changeUsers);
    }

    public static List<User> filterChange(Date curDate) {
        //future one week
        List<User> changeUsers = new ArrayList<User>();
        String sql = "select u from User u where u.installed = TRUE and datediff(YEAR, u.lastChange, ?) > 0 and datediff(YEAR, u.installDate, ?) > 0 and MONTH(u.installDate) = Month(?) and DAY(u.installDate) = DAY(?)";
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        for (int i = 0; i < 7; i ++) {

            changeUsers.addAll(User.find(sql, cal.getTime(), cal.getTime(), cal.getTime(), cal.getTime()).<User>fetch());
            cal.add(Calendar.DATE, -1);

        }
        return changeUsers;
    }

    public static void list(String search, Integer size, Integer page) {
        List<User> users ;
        page = page != null ? page : 1;
        if (search.trim().length() == 0) {
            users = User.all().fetch(page, size);
        } else {
            search = search.toLowerCase();
            String temp = "%" + search + "%";
            String sql = "lower(name) like ?1 OR lower(address) like ?2 OR lower(phone) like ?3";
            users = User.find(sql, temp, temp, temp).fetch(page,size);
        }

        render(users, search, size, page);
    }

    public static void finishChange(long userid, Staff changer) {
        User user = User.findById(userid);
        user.lastChange = new Date();
        user.lastChanger = changer;
        user.save();
        index();
    }


}