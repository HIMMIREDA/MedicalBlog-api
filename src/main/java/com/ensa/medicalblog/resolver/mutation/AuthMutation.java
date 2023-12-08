package com.ensa.medicalblog.resolver.mutation;

import com.ensa.medicalblog.graphql.input.AuthInput;
import com.ensa.medicalblog.graphql.input.RegisterInput;
import com.ensa.medicalblog.graphql.model.Auth;
import com.ensa.medicalblog.service.AuthenticationService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@GraphQLApi
public class AuthMutation {
    private final AuthenticationService authenticationService;

    @GraphQLMutation
    public @GraphQLNonNull Auth register(@GraphQLNonNull RegisterInput registerInput) {
        return authenticationService.register(registerInput);
    }

    @GraphQLMutation
    public @GraphQLNonNull Auth authenticate(@GraphQLNonNull AuthInput authInput) {
        return authenticationService.authenticate(authInput);
    }
}
