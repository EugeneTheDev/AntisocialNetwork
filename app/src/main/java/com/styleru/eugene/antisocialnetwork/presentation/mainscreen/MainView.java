package com.styleru.eugene.antisocialnetwork.presentation.mainscreen;



import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;

public interface MainView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorPopup(String message);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void addPost(Post newPosts);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void fillOnStart(Post post, int goalSize);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setProgressVisibility(boolean visibility);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void viewPostComments(Post post);
}
