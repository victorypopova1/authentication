package models;

import controllers.Secured;
import io.ebean.Model;
import org.hibernate.validator.constraints.Email;
import play.data.validation.Constraints;
import play.mvc.Http;

import static models.User.find;
import static play.mvc.Http.Context.current;


public class ChangePassword extends Model {


    @Constraints.Required(message = "Обязательное поле")
    public String password;

    @Constraints.Required(message = "Обязательное поле")
    public String newPassword;

    @Constraints.Required(message = "Обязательное поле")
    public String newPassword1;

    // Валидация формы
    public String validate() {
        // Проверка соответствия логина и пароля
        User user = find.byId(new Secured().getUsername(current()));
        if((user != null)&&(user.checkPassword(password))&&(newPassword.equals(newPassword1))){
            User.changePassword(newPassword);
            return null;
        }
        else
            return "Ошибка!!!";
    }
}