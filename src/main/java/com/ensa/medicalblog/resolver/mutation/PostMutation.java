package com.ensa.medicalblog.resolver.mutation;

import com.ensa.medicalblog.graphql.input.PostInput;
import com.ensa.medicalblog.graphql.model.Post;
import com.ensa.medicalblog.service.PostService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@GraphQLApi
public class PostMutation {
    private PostService postService;

    @GraphQLMutation
    public @GraphQLNonNull Post createPost(@GraphQLNonNull PostInput postInput){
        return postService.createPost(postInput);
    }

}
