package com.styleru.eugene.antisocialnetwork.domain.entity;


import org.parceler.Parcel;

import java.util.Objects;

@Parcel
public class User {

    public int id;

    public String name;

    public String username;

    public String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, username, email);
    }
}
