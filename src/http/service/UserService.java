package http.service;

import http.dao.UserDao;
import http.dto.CreateUserDto;
import http.dto.UserDto;
import http.exception.ValidationException;
import http.mapper.CreateUserMapper;
import http.mapper.UserMapper;
import http.validator.CreateUserValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    public Optional<UserDto> login (String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }

    @SneakyThrows
    public Integer create(CreateUserDto userDto) {
        // validation
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        // map
        var userEntity = createUserMapper.mapFrom(userDto);

        imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());

        // userDao.save
        userDao.save(userEntity);

        // return id
        return userEntity.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
