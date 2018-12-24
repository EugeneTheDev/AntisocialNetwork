package com.styleru.eugene.antisocialnetwork.presentation.commentsscreen;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.styleru.eugene.antisocialnetwork.domain.entity.Comment;

import java.util.List;

public interface CommentsView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorPopup(String message);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setComments(List<Comment> comments);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setProgressVisibility(boolean isVisible);
}
