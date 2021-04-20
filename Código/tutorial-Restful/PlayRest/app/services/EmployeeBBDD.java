package services;
import entities.Employee;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.*;
import play.db.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmployeeBBDD {

    Database database = Databases.createFrom("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/tfg");

    private static Connection con;
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String pass="root";
    private static final String url="jdbc:mysql://localhost:3306/tfg";


    private Statement createStatement;

    private ResultSet rS;

    protected boolean conector() throws SQLException, ClassNotFoundException {
        // Reseteamos a null la conexion a la bd
        con=null;
        boolean valor=false;
        try{

            int i=0;
            //  String valor= "fallo";
            Class.forName(driver);
            // Nos conectamos a la bd
            con= (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (con!=null){

                createStatement = con.createStatement();
                valor= true;
            }
        }
        //Si la conexion NO fue exitosa mostramos un mensaje de error
        catch (ClassNotFoundException | SQLException e){
            System.out.println( e);
        }
        return  valor;
    }

    protected void addEmployee(String name,String department,  String pass, int salary ) throws SQLException, ClassNotFoundException {
        if (conector() == true) {

            createStatement.executeUpdate("INSERT INTO employee (name,department,salary) VALUES ('" + name + "', '" + department + "', '" + salary + "')");
            con.close();
        }
    }
        protected ArrayList<Employee> getAllEmployees() {
            ArrayList<Employee> empleadoLista = new ArrayList();
            try {
                if(conector()==true){
                    String queryBBDD = "select * from employee";
                    int i=0;
                    try {
                        rS = createStatement.executeQuery(queryBBDD);
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        while (rS.next()) {
                            Employee empleado = new Employee();
                            empleado.setId(Integer.parseInt(rS.getString("id")));
                            empleado.setName(rS.getString("name"));
                            empleado.setDepartment(rS.getString("department"));
                            empleado.setSalary(Integer.parseInt(rS.getString("email")));

                           empleadoLista.add(empleado);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        i=0;
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                else{
                    return empleadoLista;
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return empleadoLista;
        }

    protected void updateEmployee(int id,String name,String department, int salary ) throws SQLException, ClassNotFoundException {
        try {
        if (conector() == true) {
            String queryBBDD = "update employee set name='"+name+"', department='"+department+"',salary="+salary+"where id="+id+";";

            try {
                createStatement.executeUpdate(queryBBDD);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {

                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{

        }
    } catch (SQLException ex) {
        Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    protected void deleteEmployee(int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                String queryBBDD = "delete from employee where id="+id+";";

                try {
                    createStatement.executeUpdate(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    /*
    private static EmployeeService instance;
    private Map<Integer, Employee> employees = new HashMap<>();

    public static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    public Employee addEmployee(Employee employee) {
        int id = employees.size()+1;
        employee.setId(id);
        employees.put(id, employee);
        return employee;
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public Set<Employee> getAllEmployees() {
        return new HashSet<>(employees.values());
    }

    public Employee updateEmployee(Employee employee) {
        int id = employee.getId();
        if (employees.containsKey(id)) {
            employees.put(id, employee);
            return employee;
        }
        return null;
    }

    public boolean deleteEmployee(int id) {
        return employees.remove(id) != null;
    }
    */

}
