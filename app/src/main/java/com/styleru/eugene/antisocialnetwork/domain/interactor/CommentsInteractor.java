package com.styleru.eugene.antisocialnetwork.domain.interactor;

import com.styleru.eugene.antisocialnetwork.domain.entity.Comment;
import com.styleru.eugene.antisocialnetwork.domain.interactor.funcinterfaces.Result;
import com.styleru.eugene.antisocialnetwork.domain.repository.ISocNetworkRepository;
import com.styleru.eugene.antisocialnetwork.domain.interactor.funcinterfaces.Failure;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CommentsInteractor {

    private ISocNetworkRepository socNetworkRepository;

    @Inject
    CommentsInteractor(ISocNetworkRepository socNetworkRepository) {
        this.socNetworkRepository = socNetworkRepository;
    }

    public void requestComments(int postId, Result<List<Comment>> result, Failure failure){
        socNetworkRepository.requestComments(postId, result, failure);
    }

}
