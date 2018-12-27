package com.styleru.eugene.antisocialnetwork.domain.repository;

import com.styleru.eugene.antisocialnetwork.domain.entity.Comment;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.entity.User;
import com.styleru.eugene.antisocialnetwork.domain.interactor.funcinterfaces.Result;
import com.styleru.eugene.antisocialnetwork.presentation.funcinterfaces.Failure;

import java.util.List;

public interface ISocNetworkRepository {
    void requestPost(int postId, Result<Post> result, Failure failure);
    void requestUser(int userId, Result<User> result, Failure failure);
    void requestUserByUsername(String username, Result<User> result, Failure failure);
    void requestComments(int postId, Result<List<Comment>> result, Failure failure);
    void uploadPost(String title, String body, int userId, Result<Post> result, Failure failure);

}
