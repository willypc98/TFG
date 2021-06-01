package controllers;

import entities.Laboratorio;
import entities.Usuario;
import play.*;
import play.mvc.Http;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.api.libs.json.*;
import play.mvc.Controller;
import play.mvc.Result;

import services.LaboratorioBBDD;
import services.UsuarioBBDD;
import utils.ApplicationUtil;
import java.sql.SQLException;
import java.util.ArrayList;

public class LaboratorioController extends Controller {
    private static final Logger logger = LoggerFactory.getLogger("controller");


    public Result create(Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In LaboratorioBBDD.create(), input is: {}", json.toString());
        Laboratorio lab = LaboratorioBBDD.getInstance().addLaboratorio(Json.fromJson(json, Laboratorio.class));
        JsonNode jsonObject = Json.toJson(lab);
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

}
