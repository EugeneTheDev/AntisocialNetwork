package com.styleru.eugene.antisocialnetwork.presentation.mainscreen;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.interactor.PostsInteractor;

import javax.inject.Inject;

@InjectViewState
public class MainScreenPresenter extends MvpPresenter<MainView> {

    private String internetErrorMessage;

    private PostsInteractor postsInteractor;

    @Inject
    MainScreenPresenter(PostsInteractor postsInteractor) {
        this.postsInteractor = postsInteractor;
    }

    void onStart() {
        postsInteractor.requestPosts(posts -> {
                    getViewState().setProgressVisibility(false);
                    getViewState().addPosts(posts);
                },
                type -> getViewState().showErrorPopup(internetErrorMessage));
    }

    void onPostClicked(Post post){
        getViewState().showPostComments(post);
    }


    void setErrorMessage(String internetErrorMessage) {
        this.internetErrorMessage = internetErrorMessage;
    }
}
