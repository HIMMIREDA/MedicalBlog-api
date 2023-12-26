package com.ensa.medicalblog.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    private String email;
    private String firstname;
    private String lastname;
    private String token;
}
