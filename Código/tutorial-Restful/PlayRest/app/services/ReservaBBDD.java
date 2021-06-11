package services;

import entities.Reserva;
import entities.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservaBBDD extends ConexionBBDD{

    private static ReservaBBDD instance;
    public static ReservaBBDD getInstance() {
        if (instance == null) {
            instance = new ReservaBBDD();
        }
        return instance;
    }

}
