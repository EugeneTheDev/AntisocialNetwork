package com.styleru.eugene.antisocialnetwork.domain.interactor;


import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.entity.User;
import com.styleru.eugene.antisocialnetwork.domain.repository.ISocNetworkRepository;
import com.styleru.eugene.antisocialnetwork.presentation.funcinterfaces.Failure;
import com.styleru.eugene.antisocialnetwork.presentation.funcinterfaces.Success;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CreatePostInteractor {

    private ISocNetworkRepository socNetworkRepository;

    @Inject
    public CreatePostInteractor(ISocNetworkRepository socNetworkRepository) {
        this.socNetworkRepository = socNetworkRepository;
    }

    public void uploadPost(String username, String title, String body, Success<Post> success, Failure failure){
        socNetworkRepository.requestUserByUsername(username, (user)->{
            if (user == null) success.onSuccess(null);
            else finishUpload(user, title, body, success, failure);
        }, failure);
    }

    private void finishUpload(User user, String title, String body, Success<Post> success, Failure failure){
        socNetworkRepository.uploadPost(title, body, user.getId(), (post)->{
            post.setUser(user);
            success.onSuccess(post);
        }, failure);
    }

}
