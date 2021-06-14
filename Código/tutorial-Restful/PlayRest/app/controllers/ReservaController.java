package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import entities.BancoDeTrabajoShort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.BancoDeTrabajoBBDD;
import services.LaboratorioBBDD;
import services.ReservaBBDD;
import entities.Reserva;
import utils.ApplicationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.SQLException;
import java.util.Collection;

public class ReservaController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");


    public Result create(Http.Request request) throws SQLException, ClassNotFoundException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest(ApplicationUtil.createResponse("Expecting JSON data", false));
        }
        logger.debug("In ReservaBBDD.create(), input is: {}", json.toString());
        Reserva  reserva = ReservaBBDD.getInstance().addReserva(Json.fromJson(json, Reserva.class));
        JsonNode jsonObject = Json.toJson(reserva);
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }
    /*
    public Result retrieve(int id) {
        logger.debug("In ReservaController.retrieve(), retrieve Reserva with id: {}",id);
        if (ReservaBBDD.getInstance().getReserva(id) == null) {
            return notFound(ApplicationUtil.createResponse("Reserva with id:" + id + " not found", false));
        }
        JsonNode jsonObjects = Json.toJson(ReservaBBDD.getInstance().getReserva(id));

        logger.debug("In ReservaController.retrieve(), result is: {}",jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }


     */
    public Result retrieve(int id) {
        logger.debug("In ReservaController.retrieve(), retrieve Reserva with id: {}",id);
        System.out.println("In ReservaController.retrieve(), retrieve Reserva with id: {}" + id);

        if (ReservaBBDD.getInstance().getReserva(id) == null) {

            return notFound(ApplicationUtil.createResponse("Reserva with id:" + id + " not found", false));
        }

       // ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonObjects = Json.toJson(ReservaBBDD.getInstance().getReserva(id));

         //JsonNode jsonObjects = mapper.convertValue(ReservaBBDD.getInstance().getReserva(id),JsonNode.class);

        logger.debug("In ReservaController.retrieve(), result is: {}",jsonObjects.toString());
        return ok(ApplicationUtil.createResponse(jsonObjects, true));
    }

    public Result listReservas() {
        Collection<Reserva> result = ReservaBBDD.getInstance().getAllReservas();
        logger.debug("In ReservaController.listReservas(), result is: {}",result.toString());
       // ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = Json.toJson(result);
        //JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));

    }

    public Result delete(int id) throws SQLException, ClassNotFoundException {
        logger.debug("In ReservaController.delete(), delete banco de trabajo with id: {}",id);
        if (!ReservaBBDD.getInstance().deleteReserva(id)) {
            return notFound(ApplicationUtil.createResponse("Reserva with id:" + id + " not found", false));
        }
        return ok(ApplicationUtil.createResponse("Reserva with id:" + id + " deleted", true));
    }

}
