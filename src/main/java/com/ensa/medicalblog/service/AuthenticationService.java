package com.ensa.medicalblog.service;

import com.ensa.medicalblog.graphql.input.AuthInput;
import com.ensa.medicalblog.graphql.input.RegisterInput;
import com.ensa.medicalblog.graphql.model.Auth;

public interface AuthenticationService {
    Auth register(RegisterInput registerInput);


    Auth authenticate(AuthInput authInput);
}
