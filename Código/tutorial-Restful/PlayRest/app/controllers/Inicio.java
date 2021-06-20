package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
//import static views.Freemarker.*;

public class Inicio extends Controller {

    public Result index() {
        return ok(views.html.index.render());
    }
}
