package com.poscom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer points;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonBackReference(value="post-user")
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    @JsonBackReference(value="post-comments")
    private Set<Comment> comments = new HashSet<>();

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
