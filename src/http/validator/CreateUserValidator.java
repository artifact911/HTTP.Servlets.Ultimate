package http.validator;

import http.dto.CreateUserDto;
import http.entity.Gender;
import http.util.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    private CreateUserValidator() {
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();

        if (!LocalDateFormatter.isValid(object.getBirthday())) {
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }

        if (object.getGender() == null || Gender.valueOf(object.getGender()) == null) {
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }

        return validationResult;
    }
}
