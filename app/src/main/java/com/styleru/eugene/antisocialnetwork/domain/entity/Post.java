package com.styleru.eugene.antisocialnetwork.domain.entity;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Objects;

@Parcel
public class Post {

    @SerializedName("userId")
    private int userId;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return getUserId() == post.getUserId() &&
                getId() == post.getId() &&
                Objects.equals(getTitle(), post.getTitle()) &&
                Objects.equals(getBody(), post.getBody()) &&
                Objects.equals(getUser(), post.getUser());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUserId(), getId(), getTitle(), getBody(), getUser());
    }
}
