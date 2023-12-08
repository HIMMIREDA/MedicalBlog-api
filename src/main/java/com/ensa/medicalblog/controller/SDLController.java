package com.ensa.medicalblog.controller;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaPrinter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sdl")
@AllArgsConstructor
public class SDLController {
    private GraphQLSchema graphQLSchema;

    @GetMapping(produces = "text/plain")
    String getSDL(){
        return new SchemaPrinter(
                SchemaPrinter.Options.defaultOptions()
                        .includeDirectives(false)
                        .descriptionsAsHashComments(true)

        ).print(graphQLSchema);
    }

}
