package com.styleru.eugene.antisocialnetwork.presentation.mainscreen;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.styleru.eugene.antisocialnetwork.data.repository.SocNetworkRepository;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.interactor.PostsInteractor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@InjectViewState
public class MainScreenPresenter extends MvpPresenter<MainView> {

    private PostsInteractor postsInteractor;

    @Inject
    MainScreenPresenter(PostsInteractor postsInteractor) {
        this.postsInteractor = postsInteractor;
    }

    void requestPost(int postId){
        postsInteractor.requestPosts(postId, getViewState()::addPost,
                getViewState()::showErrorPopup);

    }

    void fillOnStart(){
        int goalSize = 20;
        for (int i = 0; i < goalSize; i++) {
            postsInteractor.requestPosts(SocNetworkRepository.MAX_ID - i,
                    (post) ->getViewState().fillOnStart(post, goalSize), getViewState()::showErrorPopup);
        }
    }

    void viewPostComments(Post post){
        getViewState().viewPostComments(post);
    }

    void hideProgressBar(){
        getViewState().setProgressVisibility(false);
    }

}
