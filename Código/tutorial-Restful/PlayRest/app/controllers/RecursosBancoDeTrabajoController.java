package controllers;

import entities.ModifHoraria;
import entities.RecursosBancoDeTrabajo;
import entities.RecursosBancoDeTrabajoShort;
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
import services.RecursosBancoDeTrabajoBBDD;
import utils.ApplicationUtil;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class RecursosBancoDeTrabajoController extends Controller {


    private static final Logger logger = LoggerFactory.getLogger("controller");


    public Result create(Http.Request request, int labID, int bancoID) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In RecursosBancoDeTrabajoBBDD.create(), input is: {}", json.toString());
        RecursosBancoDeTrabajo recurso  = RecursosBancoDeTrabajoBBDD.getInstance().addRecursosBancoDeTrabajo(Json.fromJson(json, RecursosBancoDeTrabajo.class),labID,bancoID);
        JsonNode jsonObject = Json.toJson(recurso);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION,recurso.getUrl());
    }

    public Result update(Http.Request request,int labID, int bancoID,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In RecursosBancoDeTrabajoController.update()");
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        RecursosBancoDeTrabajo recurso  = RecursosBancoDeTrabajoBBDD.getInstance().updateRecursosBancoDeTrabajo(Json.fromJson(json, RecursosBancoDeTrabajo.class),labID,bancoID,id);
        logger.debug("In RecursosBancoDeTrabajoController.update(), recurso de trabajo  is: {}",recurso );
        if (recurso  == null) {
            return notFound(ApplicationUtil.createResponse("RecursosBancoDeTrabajo not found", false));
        }

        JsonNode jsonObject = Json.toJson(recurso);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }



    public Result retrieve(Http.Request request,int labID, int bancoID,int id) {
        logger.debug("In RecursosBancoDeTrabajoController.retrieve(), retrieve usuario with id: {}", id);
        RecursosBancoDeTrabajo result = RecursosBancoDeTrabajoBBDD.getInstance().getRecursosBancoDeTrabajo(labID, bancoID, id);
        if (RecursosBancoDeTrabajoBBDD.getInstance().getRecursosBancoDeTrabajo(labID, bancoID, id) == null) {

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

                    Template template = cfg.getTemplate("recursoMissing.ftl");
                    StringWriter sw = new StringWriter();
                    Map<String, Object> mapa = new TreeMap<String, Object>();
                    mapa.put("laboratorioID",labID);
                    mapa.put("bancoID",bancoID);
                    mapa.put("recursoID",id);
                    template.process(mapa, sw);
                    output = sw.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ok(output).as("text/html");

            } else {

                return notFound(ApplicationUtil.createResponse("RecursosBancoDeTrabajo with id:" + id + " not found", false));
            }
        }


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

                Template template = cfg.getTemplate("recurso.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("recurso", result);
                mapa.put("listaDisponibilidadRecursos", result.getListaDisponibilidadRecursos());
                template.process(mapa, sw);
                output = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        } else {
            //ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonObjects = Json.toJson(RecursosBancoDeTrabajoBBDD.getInstance().getRecursosBancoDeTrabajo(labID, bancoID, id));
            // JsonNode jsonObjects = mapper.convertValue(RecursosBancoDeTrabajoBBDD.getInstance().getRecursosBancoDeTrabajo(id),JsonNode.class);

            logger.debug("In RecursosBancoDeTrabajoController.retrieve(), result is: {}", jsonObjects.toString());
            return ok(ApplicationUtil.createResponse(jsonObjects, true));
        }
    }


    public Result listRecursosBancosDeTrabajo(Http.Request request,int labID, int bancoID) {
        Collection<RecursosBancoDeTrabajoShort> result = RecursosBancoDeTrabajoBBDD.getInstance().getAllRecursosBancosDeTrabajos(labID, bancoID);
        logger.debug("In RecursosBancoDeTrabajoController.listRecursosBancoDeTrabajos(), result is: {}", result.toString());
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

                Template template = cfg.getTemplate("recursos.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("recursos", result);
                mapa.put("labID", labID);
                mapa.put("bancoID", bancoID);
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

    public Result modify(Http.Request request,int labID, int bancoID,int id) throws SQLException, ClassNotFoundException{
        logger.debug("In RecursosBancoDeTrabajoController.modify()");
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        ModifHoraria mod = RecursosBancoDeTrabajoBBDD.getInstance().modifyRecurso(Json.fromJson(json, ModifHoraria.class),labID,bancoID,id);

        if (mod == null) {
            return notFound(ApplicationUtil.createResponse("Recurso not found", false));
        }

        JsonNode jsonObject = Json.toJson(mod);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result delete(Http.Request request,int labID, int bancoID,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In RecursosBancoDeTrabajoController.retrieve(), delete recurso de trabajo with id: {}",id);
        if (!RecursosBancoDeTrabajoBBDD.getInstance().deleteRecursosBancoDeTrabajo(labID,bancoID,id)) {
            return notFound(ApplicationUtil.createResponse("RecursosBancoDeTrabajo with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("RecursosBancoDeTrabajo with id:" + id + " deleted", true));
    }


}
