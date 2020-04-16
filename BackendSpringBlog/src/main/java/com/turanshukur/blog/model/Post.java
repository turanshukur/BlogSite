package com.turanshukur.blog.model;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column
    private String title;
    @Lob
    @NotNull
    @Column
    private String content;
    @Column
    private Instant createOn;
    @Column
    private Instant updateOn;
    @Column
    @NotNull
    private String username;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Instant createOn) {
        this.createOn = createOn;
    }

    public Instant getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Instant updateOn) {
        this.updateOn = updateOn;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createOn=" + createOn +
                ", updateOn=" + updateOn +
                ", username='" + username + '\'' +
                '}';
    }
}
