package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import entities.Laboratorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.LaboratorioBBDD;
import services.ReservaBBDD;
import entities.Reserva;
import utils.ApplicationUtil;

import java.sql.SQLException;

public class ReservaController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");


    public Result create(Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In LaboratorioBBDD.create(), input is: {}", json.toString());
        Reserva  reserva = ReservaBBDD.getInstance().addReserva(Json.fromJson(json, Reserva.class));
        JsonNode jsonObject = Json.toJson(reserva);
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }
}
