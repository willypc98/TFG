package controllers;

import entities.Laboratorio;
import entities.LaboratorioShort;
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
import services.LaboratorioBBDD;
import utils.ApplicationUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,lab.getUrl());
    }

    public Result update(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In LaboratorioController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Laboratorio lab = LaboratorioBBDD.getInstance().updateLaboratorio(Json.fromJson(json, Laboratorio.class),id);
        logger.debug("In LaboratorioController.update(), laboratorio  is: {}",lab);
        if (lab == null) {
            return notFound(ApplicationUtil.createResponse("Laboratorio not found", false));
        }

        JsonNode jsonObject = Json.toJson(lab);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }



    //public Result retrieve(int id) {
        // ArrayLis<Laboratorio> result = LaboratorioBBDD.getInstance().getLaboratorio(id);
     //   logger.debug("In LaboratorioController.getLaboratorio(id), result is: {}",result.toString());
       // ObjectMapper mapper = new ObjectMapper();

       // JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
       // return ok(ApplicationUtil.createResponse(jsonData, true));
   // }






     public Result retrieve(int id) {
        logger.debug("In LaboratorioController.retrieve(), retrieve usuario with id: {}",id);
        if (LaboratorioBBDD.getInstance().getLaboratorio(id) == null) {
            return notFound(ApplicationUtil.createResponse("Laboratorio with id:" + id + " not found", false));
        }
        //ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObjects = Json.toJson(LaboratorioBBDD.getInstance().getLaboratorio(id));
       // JsonNode jsonObjects = mapper.convertValue(LaboratorioBBDD.getInstance().getLaboratorio(id),JsonNode.class);

        logger.debug("In LaboratorioController.retrieve(), result is: {}",jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }


    public Result listLaboratorios() {
        Collection<LaboratorioShort> result = LaboratorioBBDD.getInstance().getAllLaboratorios();
        logger.debug("In LaboratorioController.listLaboratorios(), result is: {}",result.toString());
        //ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = Json.toJson(result);
        //JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));

    }

    public Result delete(int id) throws SQLException, ClassNotFoundException {
        logger.debug("In LaboratorioController.retrieve(), delete laboratorio with id: {}",id);
        if (!LaboratorioBBDD.getInstance().deleteLaboratorio(id)) {
            return notFound(ApplicationUtil.createResponse("Laboratorio with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Laboratorio with id:" + id + " deleted", true));
    }


}
