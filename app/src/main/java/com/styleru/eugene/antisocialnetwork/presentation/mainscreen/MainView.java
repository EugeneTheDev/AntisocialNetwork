package com.styleru.eugene.antisocialnetwork.presentation.mainscreen;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;

import java.util.List;

public interface MainView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorPopup(String message);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void addPost(Post newPost);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void addPosts(List<Post> posts);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setProgressVisibility(boolean visibility);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showPostComments(Post post);
}
