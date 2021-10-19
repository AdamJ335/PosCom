package com.poscom.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Adam Jennings
 * @since 14/10/2021
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonBackReference(value="user-posts")
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonBackReference(value="user-comments")
    private Set<Comment> comments = new HashSet<>();

    public User(String username) {
        this.username = username;
    }
}
