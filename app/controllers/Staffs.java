package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

/**
 * Created by luhaoran on 7/6/17.
 */
@Check("admin")
@With(Secure.class)
public class Staffs extends CRUD{

}
