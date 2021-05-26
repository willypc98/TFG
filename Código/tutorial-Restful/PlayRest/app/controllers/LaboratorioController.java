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
import java.sql.SQLException;
import java.util.ArrayList;

public class LaboratorioController extends Controller {
}
