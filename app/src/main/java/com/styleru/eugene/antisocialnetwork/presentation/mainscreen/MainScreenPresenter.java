package com.styleru.eugene.antisocialnetwork.presentation.mainscreen;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.styleru.eugene.antisocialnetwork.domain.interactor.PostsInteractor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@InjectViewState
public class MainScreenPresenter extends MvpPresenter<MainView> {

    private PostsInteractor postsInteractor;

    @Inject
    public MainScreenPresenter(PostsInteractor postsInteractor) {
        this.postsInteractor = postsInteractor;
    }

    public void requestNewPost(int startId){
        postsInteractor.requestNewPosts(startId, getViewState()::addNewPost,
                getViewState()::showErrorPopup);

    }

    public void fillOnStart(){
        int goalSize = 20;
        for (int i = 0; i < goalSize; i++) {
            postsInteractor.requestNewPosts(i + 1,
                    (post) ->getViewState().fillOnStart(post, goalSize), getViewState()::showErrorPopup);
        }
    }

    public void hideProgressBar(){
        getViewState().setProgressVisibility(false);
    }

    public void test(String message){
        getViewState().showErrorPopup(message);
    }

}
