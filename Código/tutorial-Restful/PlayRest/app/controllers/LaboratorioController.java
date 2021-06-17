package controllers;

import entities.Laboratorio;
import entities.LaboratorioShort;
import entities.ModifHoraria;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import play.mvc.Http;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.LaboratorioBBDD;
import utils.ApplicationUtil;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

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

public Result modify(Http.Request request,int id) throws SQLException, ClassNotFoundException{
    logger.debug("In LaboratorioController.modify()");
    JsonNode json = request.body().asJson();
    if (json == null) {
        return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
    }
    ModifHoraria mod = LaboratorioBBDD.getInstance().modifyLaboratorio(Json.fromJson(json, ModifHoraria.class),id);

    //Laboratorio lab = LaboratorioBBDD.getInstance().modifyLaboratorio(Json.fromJson(json, ModifHoraria.class),id);

    if (mod == null) {
        return notFound(ApplicationUtil.createResponse("Laboratorio not found", false));
    }

    JsonNode jsonObject = Json.toJson(mod);
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


    public Result listLaboratorios(Http.Request request) throws IOException, TemplateException {
        Collection<LaboratorioShort> result = LaboratorioBBDD.getInstance().getAllLaboratorios();
        logger.debug("In LaboratorioController.listLaboratorios(), result is: {}", result.toString());
        //ObjectMapper mapper = new ObjectMapper();

        if (request.accepts("text/html")) {
            String output="error";
            try {


                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates/");
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
                cfg.setLogTemplateExceptions(false);

                cfg.setWrapUncheckedExceptions(true);
                cfg.setFallbackOnNullLoopVariable(false);
                cfg.setNumberFormat("computer");

                Template template = cfg.getTemplate("laboratorios.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("laboratorios", result);
                mapa.put("user", "Manolo");
                template.process(mapa, sw);
            output=sw.toString();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        } else {
            JsonNode jsonData = Json.toJson(result);
            //JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
            return ok(ApplicationUtil.createResponse(jsonData, true));

        }
    }

    public Result delete(int id) throws SQLException, ClassNotFoundException {
        logger.debug("In LaboratorioController.retrieve(), delete laboratorio with id: {}",id);
        if (!LaboratorioBBDD.getInstance().deleteLaboratorio(id)) {
            return notFound(ApplicationUtil.createResponse("Laboratorio with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Laboratorio with id:" + id + " deleted", true));
    }


}
