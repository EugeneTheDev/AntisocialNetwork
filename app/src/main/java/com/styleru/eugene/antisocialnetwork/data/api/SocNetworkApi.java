package com.styleru.eugene.antisocialnetwork.data.api;


import com.styleru.eugene.antisocialnetwork.domain.entity.Comment;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SocNetworkApi {

    String BASE_URL = "http://jsonplaceholder.typicode.com/";

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int postId);

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int userId);

    @GET("/posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

}
