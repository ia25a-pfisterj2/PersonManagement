package service;

import java.sql.*;
import java.util.ArrayList;

import jdbc.DBConnector;
import model.Person;

public class PersonService {	
	public static ArrayList<Person> getPersonen() {
        ArrayList<Person> personen = new ArrayList<>();
        String sql = "SELECT * FROM personen";
        try (Connection conn = DBConnector.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Person person = new Person(
                		rs.getString("uuid"),
                        rs.getString("vorname"),
                        rs.getString("nachname"),
                        rs.getDate("geburtsdatum").toLocalDate()
                );
                personen.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personen;
    }
		
	public static boolean insert(Person person) {
        PreparedStatement preparedStatement = null;

        Connection conn = DBConnector.getInstance();

        String insertSQL = "INSERT INTO personen (uuid, vorname, nachname, geburtsdatum) VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = conn.prepareStatement(insertSQL);

            preparedStatement.setString(1, person.getUuid());
            preparedStatement.setString(2, person.getVorname());
            preparedStatement.setString(3, person.getNachname());
            preparedStatement.setDate(4, Date.valueOf(person.getGeburtsdatum()));

            int updatedRowCount = preparedStatement.executeUpdate();

            if (updatedRowCount > 0) {
                System.out.println("Wert erfolgreich eingefügt.");
                conn.commit();
                return true;
            } else {
                conn.rollback();
                System.out.println("Wert wurde nicht erfolgreich eingefügt.");
            }


        } catch (SQLException e) {
            try{
                conn.rollback();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBConnector.closeResources(preparedStatement, null);
        }

        return false;
    }
	
	public static boolean delete(String index) {
        Connection conn = DBConnector.getInstance();

        String deleteSQL = "DELETE FROM personen WHERE uuid = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(deleteSQL);

            preparedStatement.setString(1, index);

            int updatedRowCount = preparedStatement.executeUpdate();

            if (updatedRowCount > 0) {
                System.out.println("Wert erfolgreich gelöscht.");
                conn.commit();
                return true;
            } else {
                System.out.println("Wert wurde nicht erfolgreich gelöscht.");
                conn.rollback();
            }

        } catch (SQLException e) {
            try{
                conn.rollback();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBConnector.closeResources(preparedStatement, null);
        }

        return false;
    }
	
	public static boolean update(String index, Person person) {

        Connection conn = DBConnector.getInstance();

        String updateSQL = "UPDATE personen SET vorname = ?, nachname = ?, geburtsdatum = ? WHERE uuid = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(updateSQL);

            preparedStatement.setString(1, person.getVorname());
            preparedStatement.setString(2, person.getNachname());
            preparedStatement.setDate(3, Date.valueOf(person.getGeburtsdatum()));
            
            preparedStatement.setString(4, index);

            int updatedRowCount = preparedStatement.executeUpdate();

            if (updatedRowCount > 0) {
                System.out.println("Wert erfolgreich updated.");
                conn.commit();
                return true;
            } else {
                System.out.println("Wert wurde nicht erfolgreich updated.");
                conn.rollback();
            }


        } catch (SQLException e) {
            try{
                conn.rollback();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
            e.printStackTrace();

        } finally {
            DBConnector.closeResources(preparedStatement, null);
        }

        return false;
    }
}
