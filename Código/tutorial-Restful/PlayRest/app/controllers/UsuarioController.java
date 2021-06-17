package controllers;

import entities.Usuario;
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
import services.UsuarioBBDD;
import utils.ApplicationUtil;
import views.Freemarker;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class UsuarioController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");


    public Result create(Http.Request request) throws SQLException, ClassNotFoundException {
        System.out.println("Información");
        System.out.println(request.body());
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In UsuarioController.create(), input is: {}", json.toString());
        Usuario usu = UsuarioBBDD.getInstance().addUsuario(Json.fromJson(json, Usuario.class));
        JsonNode jsonObject = Json.toJson(usu);
        return created(ApplicationUtil.createResponse(jsonObject, true)).withHeader(LOCATION, usu.getUrl());
    }

    public Result update(Http.Request request, int id) throws SQLException, ClassNotFoundException {
        logger.debug("In UsuarioController.update()");
        JsonNode json = request.body().asJson();
        //JsonNode jsonObjects = Json.toJson(UsuarioBBDD.getInstance().getUsuario(id));
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Usuario usu = UsuarioBBDD.getInstance().updateUsuario(Json.fromJson(json, Usuario.class), id);
        logger.debug("In UsuarioController.update(), usuario is: {}", usu);
        if (usu == null) {
            return notFound(ApplicationUtil.createResponse("Usuario not found", false));
        }

        JsonNode jsonObject = Json.toJson(usu);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result retrieve(Http.Request request, int id) {
        logger.debug("In UsuarioController.retrieve(), retrieve usuario with id: {}", id);
        if (UsuarioBBDD.getInstance().getUsuario(id) == null) {
            return notFound(ApplicationUtil.createResponse("Usuario with id:" + id + " not found", false));
        }
        Usuario result = UsuarioBBDD.getInstance().getUsuario(id);

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

                Template template = cfg.getTemplate("usuario.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("usuario", result);
                mapa.put("listaReservas", result.getListaReservas());
                template.process(mapa, sw);
                output = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        } else {
        JsonNode jsonObjects = Json.toJson(UsuarioBBDD.getInstance().getUsuario(id));
        logger.debug("In UsuarioController.retrieve(), result is: {}", jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
        //return ok("<html> <body><h1>Esto es una prueba"+ id +" </h1></body></html>").as("text/html");
    }

}

    public Result listUsuarios(Http.Request request) {
        ArrayList<Usuario> result = UsuarioBBDD.getInstance().getAllUsuarios();
        logger.debug("In UsuarioController.listUsuarios(), result is: {}", result.toString());

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

                Template template = cfg.getTemplate("usuarios.ftl");
                StringWriter sw = new StringWriter();
                Map<String, Object> mapa = new TreeMap<String, Object>();
                mapa.put("usuarios", result);
                template.process(mapa, sw);
                output = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ok(output).as("text/html");

        } else {

            JsonNode jsonData = Json.toJson(result);
            //JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
            //return ok(ApplicationUtil.createResponse(jsonData, true));

            //ObjectMapper mapper = new ObjectMapper();

          //  JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
            return ok(ApplicationUtil.createResponse(jsonData, true));

        }
    }

    public Result delete(Http.Request request,int id) throws SQLException, ClassNotFoundException {
        logger.debug("In UsuarioController.retrieve(), delete usuario with id: {}",id);
        if (!UsuarioBBDD.getInstance().deleteUsuario(id)) {
            return notFound(ApplicationUtil.createResponse("Usuario with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Usuario with id:" + id + " deleted", true));
    }


}
