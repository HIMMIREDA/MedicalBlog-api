package com.ensa.medicalblog.graphql.model;

import io.leangen.graphql.annotations.GraphQLNonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Author {
    @GraphQLNonNull
    private String id;
    @GraphQLNonNull
    private String firstname;
    @GraphQLNonNull
    private String lastname;
    @GraphQLNonNull
    private String email;
}
