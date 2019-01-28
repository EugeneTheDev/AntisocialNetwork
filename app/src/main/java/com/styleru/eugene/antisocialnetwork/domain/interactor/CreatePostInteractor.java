package com.styleru.eugene.antisocialnetwork.domain.interactor;


import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.entity.User;
import com.styleru.eugene.antisocialnetwork.domain.interactor.funcinterfaces.Result;
import com.styleru.eugene.antisocialnetwork.domain.repository.ISocNetworkRepository;
import com.styleru.eugene.antisocialnetwork.domain.interactor.funcinterfaces.Failure;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CreatePostInteractor {

    private ISocNetworkRepository socNetworkRepository;

    @Inject
    CreatePostInteractor(ISocNetworkRepository socNetworkRepository) {
        this.socNetworkRepository = socNetworkRepository;
    }

    public void uploadPost(String username, String title, String body, Result<Post> result, Failure failure){
        socNetworkRepository.requestUserByUsername(username, (user)->{
            if (user == null) result.onResult(null);
            else finishUpload(user, title, body, result, failure);
        }, failure);
    }

    private void finishUpload(User user, String title, String body, Result<Post> result, Failure failure){
        socNetworkRepository.uploadPost(title, body, user.getId(), (post)->{
            post.setUser(user);
            result.onResult(post);
        }, failure);
    }

}
