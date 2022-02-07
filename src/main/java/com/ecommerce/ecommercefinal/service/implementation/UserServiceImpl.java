package com.ecommerce.ecommercefinal.service.implementation;

import com.ecommerce.ecommercefinal.builder.UserBuilder;
import com.ecommerce.ecommercefinal.handler.ApiRestException;
import com.ecommerce.ecommercefinal.model.request.UserRequest;
import com.ecommerce.ecommercefinal.model.response.UserResponse;
import com.ecommerce.ecommercefinal.repository.UserRepository;
import com.ecommerce.ecommercefinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse login(String email, String password) throws ApiRestException {
        var userEmail =userRepository.findByEmail(email);
        if(userEmail ==null || userEmail.getPassword().equals(password)){
            throw new ApiRestException(email,"Email o contraseña invalidos");
        }
        return UserBuilder.documentToResponse(userEmail);
    }

    @Override
    public UserResponse register(UserRequest request) throws ApiRestException {
        validateUser(request);
        var userSaved =userRepository.save(UserBuilder.requestToDocument(request));
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
}
