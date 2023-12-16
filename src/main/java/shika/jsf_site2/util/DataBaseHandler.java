package shika.jsf_site2.util;

import shika.jsf_site2.model.Point;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBaseHandler {

    private Connection connection = null;

    public DataBaseHandler() {
        connectToDataBase();
    }

    public void connectToDataBase() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", "твой username в putty", "твой пароль из .pgpass");
            System.out.println("Успешное подключение к БД");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ошибка при подключении к БД!\n" + e);
        }
    }

    public void addPointToBD(Point point, String application_id) throws SQLException {
        if (connection != null) {
            String requestAddPoint = "INSERT INTO points (x, y, r, hit, application_id) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = connection.prepareStatement(requestAddPoint);

                ps.setDouble(1, point.getX());
                ps.setDouble(2, point.getY());
                ps.setDouble(3, point.getR());
                ps.setBoolean(4, point.isHit());
                ps.setString(5, application_id);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                System.out.println("Произошла ошибка при запросе к БД" + e);
            }
        }
    }

    public List<Point> getListOfPoints(String application_id) throws SQLException {
        List<Point> listOfPoints = new ArrayList<>();
        if (connection != null) {
            String requestSelectPoints = "SELECT * FROM points WHERE application_id = ?";
            try {
                PreparedStatement ps = connection.prepareStatement(requestSelectPoints);
                ps.setString(1, application_id);

                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    Point point = new Point();
                    point.setX(result.getDouble("x"));
                    point.setY(result.getDouble("y"));
                    point.setR(result.getDouble("r"));
                    point.setHit(result.getBoolean("hit"));
                    listOfPoints.add(point);
                }
            } catch (SQLException e) {
                System.out.println("Произошла ошибка при запросе к БД" + e);
            }
        }
        return listOfPoints;
    }
}
