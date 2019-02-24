package com.styleru.eugene.antisocialnetwork.presentation.commentsscreen;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.interactor.CommentsInteractor;

import javax.inject.Inject;
import javax.inject.Singleton;

@InjectViewState
public class CommentsScreenPresenter extends MvpPresenter<CommentsView> {

    private String internetErrorMessage;

    private CommentsInteractor commentsInteractor;

    @Inject
    CommentsScreenPresenter(CommentsInteractor commentsInteractor) {
        this.commentsInteractor = commentsInteractor;
    }

    void onStart(Post post){
        commentsInteractor.addUserToPost(post, user ->{
            post.user = user;
            getViewState().setPost(post);
        },
        type -> getViewState().showErrorPopup(internetErrorMessage));

        commentsInteractor.requestComments(post.id, comments -> {
                    getViewState().setProgressVisibility(false);
                    getViewState().setComments(comments);
                },
                type -> getViewState().showErrorPopup(internetErrorMessage));
    }

    void setErrorMessage(String internetErrorMessage){
        this.internetErrorMessage = internetErrorMessage;
    }

}
