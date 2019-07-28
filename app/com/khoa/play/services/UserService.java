package com.khoa.play.services;

import com.khoa.play.jooq.tables.pojos.User;
import com.khoa.play.mappers.UserMapper;
import com.khoa.play.models.JwtResponse;
import com.khoa.play.models.LoginDTO;
import com.khoa.play.models.UserDTO;
import com.khoa.play.repositories.UserRepository;
import com.khoa.play.utils.JwtsUtil;
import com.khoa.play.utils.PasswordUtil;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

/**
 * @author Khoa
 * @created 7/28/2019
 */
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    private JwtsUtil jwtsUtil;

    @Inject
    public UserService(UserRepository userRepository, UserMapper userMapper, JwtsUtil jwtsUtil) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtsUtil = jwtsUtil;
    }

    public CompletionStage<JwtResponse> auth(LoginDTO login) {
        return this.userRepository.getUserByEmail(login.getEmail())
                .thenCompose(userRecord -> CompletableFuture.supplyAsync(() -> {
                    try {
                        if (userRecord != null && PasswordUtil.checkPassword(login.getPassword(), userRecord.getPassword())) {
                            User user = userMapper.toPojo(userRecord);
                            return this.createToken(user);
                        } else {
                            throw new Exception("Wrong email or password");
                        }
                    } catch (Exception e) {
                        throw new CompletionException(e);
                    }
                }));
    }

    public CompletionStage<UserDTO> createUser(LoginDTO login) throws Exception {
        User user = userMapper.toPojo(login);
        return this.userRepository.createUser(user)
                .thenApply((userRecord) -> userMapper.toDto(userRecord));
    }

    private JwtResponse createToken(User user) {
        return JwtResponse.of(jwtsUtil.getJWT(user));
    }

}
