package com.poscom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Adam Jennings
 * @since 14/10/2021
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String comment;

    private Integer points;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonBackReference(value="comment-user")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonBackReference(value="comment-post")
    private Post post;

    public Comment(String comment) {
        this.comment = comment;
    }
}
