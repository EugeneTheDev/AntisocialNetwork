package com.styleru.eugene.antisocialnetwork.presentation.createpost;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;

public interface CreatePostView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void clearFields();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorPopup(String message);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void replaceButtonWithLoader(boolean isButtonVisible);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setUsernameError(String error);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setTitleError(String error);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setBodyError(String error);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void result(Post post);



}
