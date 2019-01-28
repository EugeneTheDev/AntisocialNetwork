package com.styleru.eugene.antisocialnetwork.domain.interactor;

import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.interactor.funcinterfaces.Result;
import com.styleru.eugene.antisocialnetwork.domain.repository.ISocNetworkRepository;
import com.styleru.eugene.antisocialnetwork.domain.interactor.funcinterfaces.Failure;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PostsInteractor {

    private ISocNetworkRepository socNetworkRepository;

    @Inject
    PostsInteractor(ISocNetworkRepository socNetworkRepository) {
        this.socNetworkRepository = socNetworkRepository;
    }

    public void requestPosts(int postId, Result<Post> result, Failure failure){
        socNetworkRepository.requestPost(postId,
                (post)->addUserToPost(post.getUserId(), post, result, failure), failure);
    }

    private void addUserToPost(int userId, Post post, Result<Post> result, Failure failure){
        socNetworkRepository.requestUser(userId, (user)->{
            post.setUser(user);
            result.onResult(post);
        }, failure);

    }
}
