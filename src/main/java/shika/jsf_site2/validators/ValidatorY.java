package shika.jsf_site2.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.Validator;

@FacesValidator("validateY")
public class ValidatorY implements Validator {


    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (String.valueOf(o).length() < 7) {
            double y = Double.parseDouble(String.valueOf(o));
            if (y < -5 || y > 5) throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Y - от -5 до 5!"));
        } else throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Сделай Y покороче!"));
    }
}
