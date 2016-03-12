package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;
import javax.servlet.http.Part;

/**
 *
 * @author I320736
 */
@FacesValidator(value = "FileValidator")
public class FileValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part filePath = (Part) value;

        String mimetype = filePath.getContentType();
        String type = mimetype.split("/")[0];

        if (!type.equals("image")) {
            throw new ValidatorException(new FacesMessage("Invalid file.  File must be an image"));
        }

    }
}
