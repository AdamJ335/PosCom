package com.poscom.bootstrap;

import com.poscom.model.Comment;
import com.poscom.model.Post;
import com.poscom.model.User;
import com.poscom.repository.CommentRepository;
import com.poscom.repository.PostRepository;
import com.poscom.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Adam Jennings
 * @since 14/10/2021
 * @about Fills application with dummy data
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public BootStrapData(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public void run(String... args) throws Exception {


        User sarah = new User("Sarah");
        Post sarahPost = new Post("This is about fitness", "Fitness is a very interesting thing");
        Comment sarahComment = new Comment("Yes I agree");

        createDummyProfile(sarah, sarahPost, sarahComment);

        User bob = new User("Bob");
        Post bobPost = new Post("It's all about the Cheese", "Cheese dates back to however long milk was a thing");
        Comment bobComment = new Comment("Cheese is very Tasty");

        createDummyProfile(bob, bobPost, bobComment);

        System.out.println("----------------Started In Bootstrap----------------");
        System.out.println("Number of Users: " + userRepository.count());
        System.out.println("Number of Posts: " + postRepository.count());
        System.out.println("Number of Comments: " + commentRepository.count());
    }

    private void createDummyProfile(User user, Post post, Comment comment) {

        user.getPosts().add(post);
        user.getComments().add(comment);

        post.setUser(user);
        post.getComments().add(comment);

        comment.setUser(user);
        comment.setPost(post);


        userRepository.save(user);
        postRepository.save(post);
        commentRepository.save(comment);
    }
}
