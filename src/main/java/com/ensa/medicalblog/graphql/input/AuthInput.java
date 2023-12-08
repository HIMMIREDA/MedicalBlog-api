package com.ensa.medicalblog.graphql.input;

import io.leangen.graphql.annotations.GraphQLNonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthInput {
    @GraphQLNonNull
    private String email;
    @GraphQLNonNull
    private String password;
}
