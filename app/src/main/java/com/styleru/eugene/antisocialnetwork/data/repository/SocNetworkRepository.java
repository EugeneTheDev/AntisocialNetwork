package com.styleru.eugene.antisocialnetwork.data.repository;


import com.styleru.eugene.antisocialnetwork.data.api.SocNetworkApi;
import com.styleru.eugene.antisocialnetwork.domain.callbacks.ErrorType;
import com.styleru.eugene.antisocialnetwork.domain.entity.Comment;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.entity.User;
import com.styleru.eugene.antisocialnetwork.domain.callbacks.Result;
import com.styleru.eugene.antisocialnetwork.domain.repository.ISocNetworkRepository;
import com.styleru.eugene.antisocialnetwork.domain.callbacks.Failure;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class SocNetworkRepository implements ISocNetworkRepository {

    private SocNetworkApi socNetworkApi;

    @Inject
    SocNetworkRepository(SocNetworkApi socNetworkApi) {
        this.socNetworkApi = socNetworkApi;
    }

    @Override
    public void requestPosts(Result<List<Post>> result, Failure failure) {
        socNetworkApi.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.body() != null) result.onResult(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                failure.onFailure(ErrorType.INTERNET);
            }
        });
    }

    @Override
    public void requestUser(int userId, Result<User> result, Failure failure) {
        socNetworkApi.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) result.onResult(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                failure.onFailure(ErrorType.INTERNET);
            }
        });
    }

    @Override
    public void requestUserByUsername(String username, Result<User> result, Failure failure) {
        socNetworkApi.getUserByUsername(username).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.body().isEmpty()) result.onResult(response.body().get(0));
                else failure.onFailure(ErrorType.NOT_FOND);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                failure.onFailure(ErrorType.INTERNET);
            }
        });
    }

    @Override
    public void requestComments(int postId, Result<List<Comment>> result, Failure failure) {
        socNetworkApi.getComments(postId).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.body() != null)
                    result.onResult(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                failure.onFailure(ErrorType.INTERNET);
            }
        });
    }

    @Override
    public void uploadPost(String title, String body, int userId, Result<Post> result, Failure failure) {
        socNetworkApi.uploadPost(title, body, userId).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.body() != null) result.onResult(response.body());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                failure.onFailure(ErrorType.INTERNET);
            }
        });
    }
}
