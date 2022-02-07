package com.ecommerce.ecommercefinal.service.implementation;

import com.ecommerce.ecommercefinal.builder.UserBuilder;
import com.ecommerce.ecommercefinal.cache.CacheClient;
import com.ecommerce.ecommercefinal.handler.ApiRestException;
import com.ecommerce.ecommercefinal.model.document.UserDocument;
import com.ecommerce.ecommercefinal.model.request.UserRequest;
import com.ecommerce.ecommercefinal.model.response.UserResponse;
import com.ecommerce.ecommercefinal.repository.UserRepository;
import com.ecommerce.ecommercefinal.security.JwtProvider;
import com.ecommerce.ecommercefinal.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final CacheClient<UserDocument> cacheClient;

    @Override
    public UserResponse login(String email, String password) throws ApiRestException {
        UserDocument response=null;
        var token = jwtProvider.getJWTToken(email);
        try {
            var dataFromCache=cacheClient.recover(email,UserDocument.class);
            if(Objects.nonNull(dataFromCache)){
                return UserResponse.builder().email(email).token(token).build();
            }
            var userEmail =userRepository.findByEmail(email);

            if(userEmail == null || !passwordEncoder.matches(password,userEmail.getPassword())){
                throw new ApiRestException(email,"Email o contraseña incorrectos");
            }

            saveUserInCache(userEmail);
        }catch (JsonProcessingException e){
            log.error("Error",e);
        }
        return UserResponse.builder().email(email).token(token).build();

    }

    @Override
    public UserResponse register(UserRequest request) throws ApiRestException {
        UserDocument userSaved=null;
        try {
            validateUser(request);
            request.setPassword(passwordEncoder.encode(request.getPassword()));
            userSaved =userRepository.save(UserBuilder.requestToDocument(request));
            saveUserInCache(userSaved);

        }catch (JsonProcessingException e){
            log.error("Error",e);
        }
        return UserBuilder.documentToResponse(userSaved);
    }

    void validateUser(UserRequest request) throws ApiRestException {
        var user = userRepository.findByUsername(request.getUsername());
        if (user != null) {
            throw new ApiRestException(request.getUsername(),"El usuario ya existe");
        }
        user = userRepository.findByEmail(request.getEmail());
        if (user != null) {
            throw new ApiRestException(request.getEmail(),"El email ya existe");
        }
    }
    private void saveUserInCache(UserDocument userDocument)throws JsonProcessingException{
        cacheClient.save(userDocument.getEmail(),userDocument);

    }
}
