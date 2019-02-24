package com.styleru.eugene.antisocialnetwork.domain.entity;


import org.parceler.Parcel;

import java.util.Objects;

@Parcel
public class Post {

    public int userId;

    public int id;

    public String title;

    public String body;

    public User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return userId == post.userId &&
                id == post.id &&
                Objects.equals(title, post.title) &&
                Objects.equals(body, post.body) &&
                Objects.equals(user, post.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, id, title, body, user);
    }
}
