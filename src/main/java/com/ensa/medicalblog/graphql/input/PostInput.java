package com.ensa.medicalblog.graphql.input;

import io.leangen.graphql.annotations.GraphQLNonNull;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostInput {
    @GraphQLNonNull
    private String title;

    @GraphQLNonNull
    private String content;
}
