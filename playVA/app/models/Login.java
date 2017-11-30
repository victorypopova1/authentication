package models;

import io.ebean.Model;
import org.hibernate.validator.constraints.Email;
import play.data.validation.Constraints;


/**
 * Данный класс необходим для обработки формы логина.
 * Используется для валидации двух типов.
 *
 * 1. В качестве валидации отдельных полей используются аннотации
 * @Email - строка соответствует адресу эл. почты
 * @Required - обязательное поле.
 *
 * Для задания осмысленного сообщения при наарушении данного ограничения,
 * используется параметр message.
 * @Email(message = "Некорректный адрес электронной почты")
 *
 *
 * 2. Валидация на уровне формы с помощью метода String validate()
 * Когда нет возможности ограничиться валидацией полей по отдельности,
 * например когда условие валидации зависит от соответствия значений нескольких полей,
 *
 * Валидация форма логина проходит тогда, когда email и пароль соответствуют (хэши совпадают и т.д. и т.п.)
 *
 *
 */

public class Login extends Model {

    @Email(message = "Некорректный адрес электронной почты")
    @Constraints.Required(message = "Обязательное поле")
    public String email;

    @Constraints.Required(message = "Обязательное поле")
    public String password;

    // Валидация формы
    public String validate() {
        // Проверка соответствия логина и пароля
        return User.authenticate(email, password);
    }
}