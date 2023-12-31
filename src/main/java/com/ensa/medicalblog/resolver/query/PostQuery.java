package com.ensa.medicalblog.resolver.query;


import com.ensa.medicalblog.graphql.model.Author;
import com.ensa.medicalblog.graphql.model.Post;
import com.ensa.medicalblog.service.PostService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@GraphQLApi
public class PostQuery {
    private PostService postService;

    @GraphQLQuery
    public Post post(@GraphQLNonNull String id) {
        return postService.getPostById(id);
    }

    @GraphQLQuery
    public @GraphQLNonNull List<@GraphQLNonNull Post> posts(int offset, int limit) {
        return postService.getPosts(offset, limit);
    }

    @GraphQLQuery
    public List<Post> postsByTag(String tag) {return postService.getPostsByTag(tag);}

}
