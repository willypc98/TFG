package controllers;

import entities.BancoDeTrabajo;
import entities.BancoDeTrabajoShort;
import entities.ModifHoraria;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
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
import services.LaboratorioBBDD;
import utils.ApplicationUtil;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class BancoDeTrabajoController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");


    public Result create(Http.Request request,int labID) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In BancoDeTrabajoBBDD.create(), input is: {}", json.toString());
        BancoDeTrabajo banco  = BancoDeTrabajoBBDD.getInstance().addBancoDeTrabajo(Json.fromJson(json, BancoDeTrabajo.class), labID);
        JsonNode jsonObject = Json.toJson(banco );
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,banco.getUrl());
    }

    public Result update(Http.Request request,int labID,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In BancoDeTrabajoController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        BancoDeTrabajo banco  = BancoDeTrabajoBBDD.getInstance().updateBancoDeTrabajo(Json.fromJson(json, BancoDeTrabajo.class),labID,id);
        logger.debug("In BancoDeTrabajoController.update(), banco de trabajo  is: {}",banco );
        if (banco  == null) {
            return notFound(ApplicationUtil.createResponse("BancoDeTrabajo not found", false));
        }

        JsonNode jsonObject = Json.toJson(banco);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }
    public Result modify(Http.Request request,int labID,int id) throws SQLException, ClassNotFoundException{
        logger.debug("In BancoDeTrabajoController.modify()");
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        ModifHoraria mod = BancoDeTrabajoBBDD.getInstance().modifyBanco(Json.fromJson(json, ModifHoraria.class),labID,id);

        if (mod == null) {
            return notFound(ApplicationUtil.createResponse("Banco not found", false));
        }

        JsonNode jsonObject = Json.toJson(mod);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }


    public Result retrieve(Http.Request request,int labID,int id) {
        logger.debug("In BancoDeTrabajoController.retrieve(), retrieve usuario with id: {}", id);
        if (BancoDeTrabajoBBDD.getInstance().getBancoDeTrabajo(labID, id) == null) {
            return notFound(ApplicationUtil.createResponse("BancoDeTrabajo with id:" + id + " not found", false));
        }
        BancoDeTrabajo result = BancoDeTrabajoBBDD.getInstance().getBancoDeTrabajo(labID, id);

        if (request.accepts("text/html")) {
            String output = "error";
            try {


                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates/");
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
                cfg.setLogTemplateExceptions(false);

                cfg.setWrapUncheckedExceptions(true);
                cfg.setFallbackOnNullLoopVariable(false);
                cfg.setNumberFormat("computer");

                Template template = cfg.getTemplate("banco.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("banco", result);
                mapa.put("disponibilidadBanco", result.getListaDisponibilidadBanco());
                mapa.put("listaRecursos", result.getListaRecursosBanco());
                template.process(mapa, sw);
                output = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        } else {
            //ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonObjects = Json.toJson(BancoDeTrabajoBBDD.getInstance().getBancoDeTrabajo(labID, id));
            // JsonNode jsonObjects = mapper.convertValue(BancoDeTrabajoBBDD.getInstance().getBancoDeTrabajo(id),JsonNode.class);

            logger.debug("In BancoDeTrabajoController.retrieve(), result is: {}", jsonObjects.toString());
            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }

    public Result listBancosDeTrabajo(Http.Request request,int labID) {
        Collection<BancoDeTrabajoShort> result = BancoDeTrabajoBBDD.getInstance().getAllBancosDeTrabajos(labID);
        logger.debug("In BancoDeTrabajoController.listBancoDeTrabajos(), result is: {}", result.toString());
        //ObjectMapper mapper = new ObjectMapper();

        if (request.accepts("text/html")) {
            String output = "error";
            try {


                Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
                cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates/");
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
                cfg.setLogTemplateExceptions(false);

                cfg.setWrapUncheckedExceptions(true);
                cfg.setFallbackOnNullLoopVariable(false);
                cfg.setNumberFormat("computer");

                Template template = cfg.getTemplate("bancos.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("bancos", result);
                mapa.put("labID", labID);
                template.process(mapa, sw);
                output = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        } else {
            JsonNode jsonData = Json.toJson(result);
            //JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
            return ok(ApplicationUtil.createResponse(jsonData, true));

        }
    }

    public Result delete(Http.Request request,int labID,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In BancoDeTrabajoController.delete(), delete banco de trabajo with id: {}",id);
        if (!BancoDeTrabajoBBDD.getInstance().deleteBancoDeTrabajo(labID,id)) {
            return notFound(ApplicationUtil.createResponse("BancoDeTrabajo with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("BancoDeTrabajo with id:" + id + " deleted", true));
    }


}
