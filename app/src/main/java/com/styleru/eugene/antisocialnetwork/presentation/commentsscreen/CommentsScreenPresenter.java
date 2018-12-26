package com.styleru.eugene.antisocialnetwork.presentation.commentsscreen;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.styleru.eugene.antisocialnetwork.domain.interactor.CommentsInteractor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@InjectViewState
public class CommentsScreenPresenter extends MvpPresenter<CommentsView> {

    private CommentsInteractor commentsInteractor;

    @Inject
    public CommentsScreenPresenter(CommentsInteractor commentsInteractor) {
        this.commentsInteractor = commentsInteractor;
    }

    public void requestComments(int postId){
        commentsInteractor.requestComments(postId, getViewState()::setComments,
                getViewState()::showErrorPopup);
    }

    public void hideProgressBar(){
        getViewState().setProgressVisibility(false);
    }

    public void showProgressBar(){
        getViewState().setProgressVisibility(true);
    }
}
