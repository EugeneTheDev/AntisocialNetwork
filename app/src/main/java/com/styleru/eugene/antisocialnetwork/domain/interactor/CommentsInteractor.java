package com.styleru.eugene.antisocialnetwork.domain.interactor;

import com.styleru.eugene.antisocialnetwork.domain.entity.Comment;
import com.styleru.eugene.antisocialnetwork.domain.repository.ISocNetworkRepository;
import com.styleru.eugene.antisocialnetwork.presentation.funcinterfaces.Failure;
import com.styleru.eugene.antisocialnetwork.presentation.funcinterfaces.Success;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CommentsInteractor {

    private ISocNetworkRepository socNetworkRepository;

    @Inject
    public CommentsInteractor(ISocNetworkRepository socNetworkRepository) {
        this.socNetworkRepository = socNetworkRepository;
    }

    public void requestComments(int postId, Success<List<Comment>> success, Failure failure){
        socNetworkRepository.requestComments(postId, success::onSuccess, failure);
    }

}
