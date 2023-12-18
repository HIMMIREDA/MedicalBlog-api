package com.ensa.medicalblog.resolver.mutation;

import com.ensa.medicalblog.config.security.LogoutService;
import com.ensa.medicalblog.graphql.input.AuthInput;
import com.ensa.medicalblog.graphql.input.RegisterInput;
import com.ensa.medicalblog.graphql.model.Auth;
import com.ensa.medicalblog.graphql.model.Logout;
import com.ensa.medicalblog.service.AuthenticationService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLRootContext;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import io.leangen.graphql.spqr.spring.autoconfigure.DefaultGlobalContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
@RequiredArgsConstructor
@GraphQLApi
public class AuthMutation {
    private final AuthenticationService authenticationService;
    private final LogoutService logoutService;

    @GraphQLMutation
    public @GraphQLNonNull Auth register(@GraphQLNonNull RegisterInput registerInput) {
        return authenticationService.register(registerInput);
    }

    @GraphQLMutation
    public @GraphQLNonNull Auth authenticate(@GraphQLNonNull AuthInput authInput) {
        return authenticationService.authenticate(authInput);
    }

    @GraphQLMutation
    public @GraphQLNonNull Logout logout(@GraphQLRootContext DefaultGlobalContext<ServletWebRequest> context) {
        return logoutService.logout(context);
    }
}
