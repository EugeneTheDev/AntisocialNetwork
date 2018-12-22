package com.styleru.eugene.antisocialnetwork.data.repository;


import com.styleru.eugene.antisocialnetwork.data.api.SocNetworkApi;
import com.styleru.eugene.antisocialnetwork.domain.entity.Comment;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.entity.User;
import com.styleru.eugene.antisocialnetwork.domain.interactor.funcinterfaces.Result;
import com.styleru.eugene.antisocialnetwork.domain.repository.ISocNetworkRepository;
import com.styleru.eugene.antisocialnetwork.presentation.funcinterfaces.Failure;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocNetworkRepository implements ISocNetworkRepository {

    private SocNetworkApi socNetworkApi;

    @Inject
    public SocNetworkRepository(SocNetworkApi socNetworkApi) {
        this.socNetworkApi = socNetworkApi;
    }

    @Override
    public void requestPost(int postId, Result<Post> result, Failure failure) {
        socNetworkApi.getPost(postId).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.body() != null) result.onResult(response.body());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                failure.onFailure(t.getMessage());
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
                failure.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void requestComments(int postId, Result<List<Comment>> result, Failure failure) {
        socNetworkApi.getComments(postId).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.body() != null && !response.body().isEmpty())
                    result.onResult(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                failure.onFailure(t.getMessage());
            }
        });
    }

}
