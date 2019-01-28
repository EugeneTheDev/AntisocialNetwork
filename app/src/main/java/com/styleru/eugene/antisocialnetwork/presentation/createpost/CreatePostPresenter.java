package com.styleru.eugene.antisocialnetwork.presentation.createpost;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.styleru.eugene.antisocialnetwork.domain.interactor.CreatePostInteractor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@InjectViewState
public class CreatePostPresenter extends MvpPresenter<CreatePostView> {

    private final String ERROR_MESSAGE = "Must not be empty";

    private CreatePostInteractor createPostInteractor;

    @Inject
    CreatePostPresenter(CreatePostInteractor createPostInteractor) {
        this.createPostInteractor = createPostInteractor;
    }

    void uploadPost(String username, String title, String body){
        boolean isCorrect = true;
        if (username == null || username.isEmpty()){
            getViewState().setUsernameError(ERROR_MESSAGE);
            isCorrect = false;
        }
        if (title == null || title.isEmpty()){
            getViewState().setTitleError(ERROR_MESSAGE);
            isCorrect = false;
        }
        if (body == null || body.isEmpty()){
            getViewState().setBodyError(ERROR_MESSAGE);
            isCorrect = false;
        }

        if (isCorrect) {
            getViewState().replaceButtonWithLoader(false);
            createPostInteractor.uploadPost(username, title, body, (post) -> {
                if (post == null) getViewState().setUsernameError("No user with such username");
                else getViewState().result(post);
                getViewState().replaceButtonWithLoader(true);
            }, getViewState()::showErrorPopup);
        }
    }

    void setText(String username, String title, String body){
        getViewState().setText(username, title, body);
    }

}
