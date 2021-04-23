package controllers;

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
import services.EmployeeBBDD;
import services.EmployeeService;
import utils.ApplicationUtil;
import org.*;
/*
import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;

*/


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
 
public class EmployeeController extends Controller {
 
    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result create(Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In EmployeeController.create(), input is: {}", json.toString());
        Employee employee = EmployeeBBDD.getInstance().addEmployee(Json.fromJson(json, Employee.class));
        JsonNode jsonObject = Json.toJson(employee);
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }
    /*
    public Result create(Http.Request request) {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In EmployeeController.create(), input is: {}", json.toString());
        Employee employee = EmployeeService.getInstance().addEmployee(Json.fromJson(json, Employee.class));
        JsonNode jsonObject = Json.toJson(employee);
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }


     */
    /*public Result update(Http.Request request) throws SQLException, ClassNotFoundException {
        logger.debug("In EmployeeController.update()");
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Employee employee = EmployeeBBDD.getInstance().updateEmployee(Json.fromJson(json, Employee.class));
        logger.debug("In EmployeeController.update(), employee is: {}",employee);
        if (employee == null) {
            return notFound(ApplicationUtil.createResponse("Employee not found", false));
        }
 
        JsonNode jsonObject = Json.toJson(employee);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }
    */

    public Result update(Http.Request request) throws SQLException, ClassNotFoundException {
        logger.debug("In EmployeeController.update()");
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Employee employee = EmployeeBBDD.getInstance().updateEmployee(Json.fromJson(json, Employee.class));
        logger.debug("In EmployeeController.update(), employee is: {}",employee);
        if (employee == null) {
            return notFound(ApplicationUtil.createResponse("Employee not found", false));
        }

        JsonNode jsonObject = Json.toJson(employee);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    /*
    public Result update(Http.Request request) {
        logger.debug("In EmployeeController.update()");
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Employee employee = EmployeeService.getInstance().updateEmployee(Json.fromJson(json, Employee.class));
        logger.debug("In EmployeeController.update(), employee is: {}",employee);
        if (employee == null) {
            return notFound(ApplicationUtil.createResponse("Employee not found", false));
        }

        JsonNode jsonObject = Json.toJson(employee);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }
     */

     /*

    public Result retrieve(Http.Request request) {
        JsonNode json = request.body().asJson();
       logger.debug("In EmployeeController.retrieve(), retrieve employee with id: {}");
       //logger.debug("In EmployeeController.retrieve(), retrieve employee with id: {}",id);
        if (EmployeeBBDD.getInstance().getEmployee(Json.fromJson(json, Employee.class)) == null) {
            return notFound(ApplicationUtil.createResponse("Employee with id:" + /*+ id +*/ /*" not found", false));
        }
        Employee employee = EmployeeBBDD.getInstance().getEmployee(Json.fromJson(json, Employee.class));
        JsonNode jsonObjects = Json.toJson(employee);
        logger.debug("In EmployeeController.retrieve(), result is: {}",jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }
    */
    public Result retrieve(int id) {
        logger.debug("In EmployeeController.retrieve(), retrieve employee with id: {}",id);
        if (EmployeeBBDD.getInstance().getEmployee(id) == null) {
            return notFound(ApplicationUtil.createResponse("Employee with id:" + id + " not found", false));
        }
        JsonNode jsonObjects = Json.toJson(EmployeeBBDD.getInstance().getEmployee(id));
        logger.debug("In EmployeeController.retrieve(), result is: {}",jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }
    /*
    public Result retrieve(int id) {
        logger.debug("In EmployeeController.retrieve(), retrieve employee with id: {}",id);
        if (EmployeeService.getInstance().getEmployee(id) == null) {
            return notFound(ApplicationUtil.createResponse("Employee with id:" + id + " not found", false));
        }
        JsonNode jsonObjects = Json.toJson(EmployeeService.getInstance().getEmployee(id));
        logger.debug("In EmployeeController.retrieve(), result is: {}",jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }


    public Result listEmployees() {
        Set<Employee> result = EmployeeBBDD.getInstance().getAllEmployees();
        logger.debug("In EmployeeController.listEmployees(), result is: {}",result.toString());
        ObjectMapper mapper = new ObjectMapper();
 
        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));
 
    }
    */
    public Result listEmployees() {
        ArrayList<Employee> result = EmployeeBBDD.getInstance().getAllEmployees();
        logger.debug("In EmployeeController.listEmployees(), result is: {}",result.toString());
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));

    }

/*
    public Result delete(Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        logger.debug("In EmployeeController.delete()");
        //logger.debug("In EmployeeController.retrieve(), delete employee with id: {}",id);
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting Json data", false));
        }
        Employee employee = EmployeeBBDD.getInstance().deleteEmployee(Json.fromJson(json, Employee.class));
        logger.debug("In EmployeeController.delete(), employee is: {}",employee);
        if (employee == null) {
            return notFound(ApplicationUtil.createResponse("Employee not found", false));
        }

        JsonNode jsonObject = Json.toJson(employee);
        return ok(ApplicationUtil.createResponse(jsonObject, true));

        //return ok(ApplicationUtil.createResponse("Employee with id:" + id + " deleted", true));
    }


 */
    public Result delete(int id) throws SQLException, ClassNotFoundException {
        logger.debug("In EmployeeController.retrieve(), delete employee with id: {}",id);
        if (!EmployeeBBDD.getInstance().deleteEmployee(id)) {
            return notFound(ApplicationUtil.createResponse("Employee with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Employee with id:" + id + " deleted", true));
    }

    /*
    public Result delete(int id) {
        logger.debug("In EmployeeController.retrieve(), delete employee with id: {}",id);
        if (!EmployeeService.getInstance().deleteEmployee(id)) {
            return notFound(ApplicationUtil.createResponse("Employee with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Employee with id:" + id + " deleted", true));
    }
     */


}