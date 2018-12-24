package com.styleru.eugene.antisocialnetwork.domain.interactor;

import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.repository.ISocNetworkRepository;
import com.styleru.eugene.antisocialnetwork.presentation.funcinterfaces.Failure;
import com.styleru.eugene.antisocialnetwork.presentation.funcinterfaces.Success;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PostsInteractor {

    private ISocNetworkRepository socNetworkRepository;

    @Inject
    public PostsInteractor(ISocNetworkRepository socNetworkRepository) {
        this.socNetworkRepository = socNetworkRepository;
    }

    public void requestPosts(int postId, Success<Post> success, Failure failure){
        socNetworkRepository.requestPost(postId,
                (post)->addUserToPost(post.getUserId(), post, success, failure), failure);
    }

    private void addUserToPost(int userId, Post post, Success<Post> success, Failure failure){
        socNetworkRepository.requestUser(userId, (user)->{
            post.setUser(user);
            success.onSuccess(post);
        }, failure);

    }
}
