package com.styleru.eugene.antisocialnetwork.presentation.createpost;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.styleru.eugene.antisocialnetwork.domain.interactor.CreatePostInteractor;

import javax.inject.Inject;
import javax.inject.Singleton;

@InjectViewState
public class CreatePostPresenter extends MvpPresenter<CreatePostView> {

    private final static String ERROR_MESSAGE = "Must not be empty!";
    private String internetErrorMessage, notFoundErrorMessage;

    private CreatePostInteractor createPostInteractor;

    @Inject
    CreatePostPresenter(CreatePostInteractor createPostInteractor) {
        this.createPostInteractor = createPostInteractor;
    }

    void onClickPost(String username, String title, String body){
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
            createPostInteractor.uploadPost(username, title, body, post -> {
                getViewState().replaceButtonWithLoader(true);
                getViewState().clearFields();
                getViewState().result(post);
            }, type -> {
                switch (type){
                    case NOT_FOND:
                        getViewState().setUsernameError(notFoundErrorMessage);
                        break;
                    case INTERNET:
                        getViewState().showErrorPopup(internetErrorMessage);
                        break;
                }
                getViewState().replaceButtonWithLoader(true);
            });
        }
    }

    void setErrorMessages(String internetErrorMessage, String notFoundErrorMessage){
        this.internetErrorMessage = internetErrorMessage;
        this.notFoundErrorMessage = notFoundErrorMessage;
    }

}
