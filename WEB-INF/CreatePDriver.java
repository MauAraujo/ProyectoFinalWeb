import java.sql.*;

public class CreatePDriver {

  CreatePDriver(ObRegistro Datos){
    try{
     Class.forName("com.mysql.jdbc.Driver");
     Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
     Statement sql = db.createStatement(
     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     ResultSet projects = sql.executeQuery("SELECT * FROM proyectos");

     projects.moveToInsertRow();
     projects.updateString("name",Datos.DameNombre());
     projects.updateString("description", Datos.DameApellido()); 
     projects.updateString("date", Datos.DameApellido2()); 
     projects.updateInt("edad",Datos.DameEdad());  
     projects.insertRow();

     projects.close();
     Conexion.close();
     SentenciaSQL.close();
    }
    catch (ClassNotFoundException e) {
      System.out.println("Clase no encontrada");
    }
    catch (SQLException e) {
      System.out.println(e);
    }    
  }

}
