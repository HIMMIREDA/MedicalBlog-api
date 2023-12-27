package com.ensa.medicalblog.config.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.GraphQL;
import io.leangen.graphql.spqr.spring.web.GraphQLController;
import io.leangen.graphql.spqr.spring.web.GraphQLExecutor;
import io.leangen.graphql.spqr.spring.web.dto.ExecutorParams;
import io.leangen.graphql.spqr.spring.web.dto.GraphQLRequest;
import io.leangen.graphql.spqr.spring.web.dto.TransportType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin
public class CustomGraphQLController extends GraphQLController<NativeWebRequest> {

    @Autowired
    public CustomGraphQLController(GraphQL graphQL, GraphQLExecutor<NativeWebRequest> executor) {
        super(graphQL, executor);
    }

    @PostMapping(
            value = "${graphql.spqr.http.endpoint:/graphql}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object executeMultipartPost(@RequestPart("operations") String operations,
                                       @RequestPart("map") String map,
                                       MultipartHttpServletRequest multiPartRequest,
                                       NativeWebRequest webRequest) throws IOException, ServletException {
        GraphQLRequest graphQLRequest = new ObjectMapper().readerFor(GraphQLRequest.class).readValue(operations);
        Map<String, ArrayList<String>> fileMap = new ObjectMapper().readerFor(Map.class).readValue(map);

        mapRequestFilesToVariables(multiPartRequest, graphQLRequest, fileMap);

        return this.executeJsonPost(graphQLRequest, graphQLRequest, webRequest);
    }
    private void mapRequestFilesToVariables(MultipartHttpServletRequest multiPartRequest, GraphQLRequest graphQLRequest,
                                            Map<String, ArrayList<String>> fileMap) throws IOException, ServletException {
        for (var pair : fileMap.entrySet()) {
            String targetVariable = pair.getValue().get(0).replace("variables.", "");
            if (graphQLRequest.getVariables().containsKey(targetVariable)) {
                Part correspondingFile = multiPartRequest.getPart(pair.getKey());
                graphQLRequest.getVariables().put(targetVariable, correspondingFile);
            }
        }
    }
}
