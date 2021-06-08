package controllers;

import entities.BancoDeTrabajo;
import play.*;
import play.mvc.Http;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.api.libs.json.*;
import play.mvc.Controller;
import play.mvc.Result;
import services.BancoDeTrabajoBBDD;
import utils.ApplicationUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class BancoDeTrabajoController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");


    public Result create(Http.Request request,int labID) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In BancoDeTrabajoBBDD.create(), input is: {}", json.toString());
        BancoDeTrabajo banco  = BancoDeTrabajoBBDD.getInstance().addBancoDeTrabajo(Json.fromJson(json, BancoDeTrabajo.class));
        JsonNode jsonObject = Json.toJson(banco );
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result update(Http.Request request,int labID,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In BancoDeTrabajoController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        BancoDeTrabajo banco  = BancoDeTrabajoBBDD.getInstance().updateBancoDeTrabajo(Json.fromJson(json, BancoDeTrabajo.class),id);
        logger.debug("In BancoDeTrabajoController.update(), banco de trabajo  is: {}",banco );
        if (banco  == null) {
            return notFound(ApplicationUtil.createResponse("BancoDeTrabajo not found", false));
        }

        JsonNode jsonObject = Json.toJson(banco);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }



    public Result retrieve(int labID,int id) {
        logger.debug("In BancoDeTrabajoController.retrieve(), retrieve usuario with id: {}",id);
        if (BancoDeTrabajoBBDD.getInstance().getBancoDeTrabajo(id) == null) {
            return notFound(ApplicationUtil.createResponse("BancoDeTrabajo with id:" + id + " not found", false));
        }
        //ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObjects = Json.toJson(BancoDeTrabajoBBDD.getInstance().getBancoDeTrabajo(id));
        // JsonNode jsonObjects = mapper.convertValue(BancoDeTrabajoBBDD.getInstance().getBancoDeTrabajo(id),JsonNode.class);

        logger.debug("In BancoDeTrabajoController.retrieve(), result is: {}",jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }


    public Result listBancosDeTrabajo(int labID) {
        Collection<BancoDeTrabajo> result = BancoDeTrabajoBBDD.getInstance().getAllBancosDeTrabajos();
        logger.debug("In BancoDeTrabajoController.listBancoDeTrabajos(), result is: {}",result.toString());
        //ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = Json.toJson(result);
        //JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));

    }

    public Result delete(int labID,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In BancoDeTrabajoController.retrieve(), delete banco de trabajo with id: {}",id);
        if (!BancoDeTrabajoBBDD.getInstance().deleteBancoDeTrabajo(id)) {
            return notFound(ApplicationUtil.createResponse("BancoDeTrabajo with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("BancoDeTrabajo with id:" + id + " deleted", true));
    }


}
