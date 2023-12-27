package com.ensa.medicalblog.service.impl;

import com.ensa.medicalblog.entity.*;
import com.ensa.medicalblog.graphql.input.CommentInput;
import com.ensa.medicalblog.graphql.input.PostInput;
import com.ensa.medicalblog.graphql.model.Post;
import com.ensa.medicalblog.mapper.PostMapper;
import com.ensa.medicalblog.repository.*;
import com.ensa.medicalblog.service.ImageService;
import com.ensa.medicalblog.service.PostService;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ImageService imageService;
    private TagRepository tagRepository;
    private PostTagRepository postTagRepository;
    private UserRepository userRepository;
    private LikeRepository likeRepository;

    @Override
    public Post createPost(PostInput createPostRequest, Part file) throws IOException {
        PostEntity postEntity = PostMapper.toEntity(createPostRequest);
        postEntity.setImage(imageService.uploadFile(file));
        postEntity = postRepository.save(postEntity);

        List<TagEntity> postTags = createPostRequest.getTags().stream()
                .filter(tag -> tagRepository.findByTagName(tag).isEmpty())
                .map(tagName -> TagEntity.builder().tagName(tagName).build())
                .toList();

        tagRepository.saveAll(postTags);

        for (String tagName : createPostRequest.getTags()) {
            PostTagEntity postTagEntity = PostTagEntity.builder().tagId(tagRepository.findByTagName(tagName).get().getId()).postId(postEntity.getId()).build();
            postTagRepository.save(postTagEntity);
        }

        return PostMapper.toModel(postEntity);
    }

    @Override
    public Post getPostById(String id) {
        // @TODO: change exception handling later
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        List<CommentEntity> sortedComments = postEntity.getComments().stream()
                .sorted(Comparator.comparing(CommentEntity::getCreatedAt, Comparator.reverseOrder()))
                .toList();
        postEntity.setComments(sortedComments);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Post post = PostMapper.toModel(postEntity);
        if(authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()){
            String username = authentication.getName();
            System.out.println(username);
            UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User Not Found"));
            Optional<LikeEntity> likeEntityOptional = likeRepository.findByPostIdAndUserId(postEntity.getId(), user.getId());
            post.setIsLiked(likeEntityOptional.isPresent() && (likeEntityOptional.get().getLiked()));
            System.out.println("post is liked "+ likeEntityOptional.get().getLiked());
        }else{
            post.setIsLiked(false);
        }


        return post;
    }


    @Override
    public List<Post> getPostsByTag(String tag) {
        return postTagRepository.findByTagId(tag).stream()
                .map(postTag -> Post.builder()
                        .id(postTag.getPostId())
                        .title(getPostById(postTag.getPostId()).getTitle())
                        .content(getPostById(postTag.getPostId()).getContent())
                        .createdAt(getPostById(postTag.getPostId()).getCreatedAt())
                        .tags(getPostById(postTag.getPostId()).getTags())
                        .likes(getPostById(postTag.getPostId()).getLikes())
                        .comments(getPostById(postTag.getPostId()).getComments())
                        .build())
                .toList();

    }

    @Override
    public List<Post> getPosts() {

        return postRepository.findAll().stream().map(PostMapper::toModel).toList();
    }

    @Override
    public CommentEntity comment(CommentInput commentInput) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User Not Found"));

        PostEntity postEntity = postRepository.findById(commentInput.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));
        CommentEntity commentEntity = CommentEntity.builder()
                .id(new ObjectId().toString())
                .content(commentInput.getContent())
                .userId(user.getId())
                .postId(postEntity.getId())
                .username(user.getFirstname() + "_" + user.getLastname())
                .createdAt(LocalDateTime.now())
                .build();
        postEntity.getComments().add(commentEntity);
        postRepository.save(postEntity);

        return commentEntity;
    }

    @Override
    public void react(String postId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User Not Found"));

        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        Optional<LikeEntity> likeEntityOptional = likeRepository.findByPostIdAndUserId(postId, user.getId());
        if (!likeEntityOptional.isPresent()) {
            postEntity.setLikes(postEntity.getLikes() + 1);

            postRepository.save(postEntity);

            LikeEntity likeEntity = LikeEntity.builder()
                    .userId(user.getId())
                    .postId(postEntity.getId())
                    .liked(true)
                    .build();
            likeRepository.save(likeEntity);
        }
        else{
            if(likeEntityOptional.get().getLiked().equals(false)){
                postEntity.setLikes(postEntity.getLikes() + 1);
                postRepository.save(postEntity);

                likeEntityOptional.get().setLiked(true);
                likeRepository.save(likeEntityOptional.get());
            }
            else{
                postEntity.setLikes(postEntity.getLikes() - 1);
                postRepository.save(postEntity);

                likeEntityOptional.get().setLiked(false);
                likeRepository.save(likeEntityOptional.get());
            }
        }

    }
}
