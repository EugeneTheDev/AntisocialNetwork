package com.styleru.eugene.antisocialnetwork.data.api;


import com.styleru.eugene.antisocialnetwork.domain.entity.Comment;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SocNetworkApi {

    String BASE_URL = "http://jsonplaceholder.typicode.com/";

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int postId);

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int userId);

    @GET("users")
    Call<List<User>> getUserByUsername(@Query("username") String username);

    @GET("/comments")
    Call<List<Comment>> getComments(@Query("postId") int postId);

    @POST("/posts")
    @FormUrlEncoded
    Call<Post> uploadPost(@Field("title") String title, @Field("body") String body,
                          @Field("userId") int userId);

}
