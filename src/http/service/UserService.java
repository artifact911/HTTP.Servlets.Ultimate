package http.service;

import http.dao.UserDao;
import http.dto.CreateUserDto;
import http.entity.User;
import http.exception.ValidationException;
import http.mapper.CreateUserMapper;
import http.validator.CreateUserValidator;
import http.validator.ValidationResult;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();

    private final UserDao userDao = UserDao.getInstance();

    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    private UserService() {
    }

    public Integer create(CreateUserDto userDto) {
        // validation
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        // map
        var userEntity = createUserMapper.mapFrom(userDto);

        // userDao.save
        userDao.save(userEntity);

        // return id
        return userEntity.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
