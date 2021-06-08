package controllers;

import entities.RecursosBancoDeTrabajo;
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
import services.RecursosBancoDeTrabajoBBDD;
import services.EmployeeBBDD;
import services.EmployeeService;
import utils.ApplicationUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class RecursosBancoDeTrabajoController extends Controller {


    private static final Logger logger = LoggerFactory.getLogger("controller");


    public Result create(Http.Request request, int labID, int bancoID) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In RecursosBancoDeTrabajoBBDD.create(), input is: {}", json.toString());
        RecursosBancoDeTrabajo recurso  = RecursosBancoDeTrabajoBBDD.getInstance().addRecursosBancoDeTrabajo(Json.fromJson(json, RecursosBancoDeTrabajo.class));
        JsonNode jsonObject = Json.toJson(recurso );
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update(Http.Request request,int labID, int bancoID,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In RecursosBancoDeTrabajoController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        RecursosBancoDeTrabajo recurso  = RecursosBancoDeTrabajoBBDD.getInstance().updateRecursosBancoDeTrabajo(Json.fromJson(json, RecursosBancoDeTrabajo.class),id);
        logger.debug("In RecursosBancoDeTrabajoController.update(), recurso de trabajo  is: {}",recurso );
        if (recurso  == null) {
            return notFound(ApplicationUtil.createResponse("RecursosBancoDeTrabajo not found", false));
        }

        JsonNode jsonObject = Json.toJson(recurso);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }



    public Result retrieve(int labID, int bancoID,int id) {
        logger.debug("In RecursosBancoDeTrabajoController.retrieve(), retrieve usuario with id: {}",id);
        if (RecursosBancoDeTrabajoBBDD.getInstance().getRecursosBancoDeTrabajo(id) == null) {
            return notFound(ApplicationUtil.createResponse("RecursosBancoDeTrabajo with id:" + id + " not found", false));
        }
        //ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObjects = Json.toJson(RecursosBancoDeTrabajoBBDD.getInstance().getRecursosBancoDeTrabajo(id));
        // JsonNode jsonObjects = mapper.convertValue(RecursosBancoDeTrabajoBBDD.getInstance().getRecursosBancoDeTrabajo(id),JsonNode.class);

        logger.debug("In RecursosBancoDeTrabajoController.retrieve(), result is: {}",jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }


    public Result listRecursosBancosDeTrabajo(int labID, int bancoID) {
        Collection<RecursosBancoDeTrabajo> result = RecursosBancoDeTrabajoBBDD.getInstance().getAllRecursosBancosDeTrabajos();
        logger.debug("In RecursosBancoDeTrabajoController.listRecursosBancoDeTrabajos(), result is: {}",result.toString());
        //ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = Json.toJson(result);
        //JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));

    }

    public Result delete(int labID, int bancoID,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In RecursosBancoDeTrabajoController.retrieve(), delete recurso de trabajo with id: {}",id);
        if (!RecursosBancoDeTrabajoBBDD.getInstance().deleteRecursosBancoDeTrabajo(id)) {
            return notFound(ApplicationUtil.createResponse("RecursosBancoDeTrabajo with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("RecursosBancoDeTrabajo with id:" + id + " deleted", true));
    }


}
