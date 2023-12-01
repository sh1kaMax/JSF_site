package shika.jsf_site2.util;

import shika.jsf_site2.model.Point;

import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;



public class HitBean implements Serializable {
    private final String application_id = UUID.randomUUID().toString();
    private Point point;
    private Point lastPoint;
    private final DataBaseHandler db;

    public HitBean() {
        point = new Point();
        point.setX(0.0);
        point.setR(2.0);
        db = new DataBaseHandler();
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getLastPoint() { return lastPoint;}


    public void addPoint() throws SQLException {
        if (checkParams() && checkY()) {
            point.checkHit();
            db.addPointToBD(point, application_id);
            lastPoint = point;
            point = new Point();
            point.setX(0.0);
            point.setR(2.0);
        }
    }

    public void setX(String value) {
        Double x = Double.parseDouble(String.valueOf(value));
        point.setX(x);
    }

    public void setY() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String yValue = params.get("info:y");
        Double y = Double.parseDouble(yValue);
        point.setY(y);
    }

    public void setR(AjaxBehaviorEvent event) {
        UIInput input = (UIInput) event.getSource();
        point.setR(Double.valueOf(input.getValue().toString()));
    }

    public List<Point> getPoints() {
        List<Point> listOfPoints = new ArrayList<>();
        try {
            listOfPoints = db.getListOfPoints(application_id);
        } catch (SQLException e) {
            System.out.println("Ошибка с БД" + e);
        }
        return listOfPoints;
    }

    private boolean checkParams() {
        return point.getX() != null && point.getY() != null && point.getR() != null;
    }

    private boolean checkY() {
        FacesContext context = FacesContext.getCurrentInstance();
        String y = context.getExternalContext().getRequestParameterMap().get("info:y");
        return y != null;
    }
}
