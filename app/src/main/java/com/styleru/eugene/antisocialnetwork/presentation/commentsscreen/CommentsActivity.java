package com.styleru.eugene.antisocialnetwork.presentation.commentsscreen;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.styleru.eugene.antisocialnetwork.AntisocialNetworkApp;
import com.styleru.eugene.antisocialnetwork.R;
import com.styleru.eugene.antisocialnetwork.domain.entity.Comment;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.presentation.mainscreen.MainActivity;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsActivity extends MvpAppCompatActivity implements CommentsView {

    @BindView(R.id.comments_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.comments_loader)
    ProgressBar progressBar;

    @Inject
    @InjectPresenter
    CommentsScreenPresenter commentsScreenPresenter;

    @Inject
    CommentsAdapter commentsAdapter;


    @ProvidePresenter
    CommentsScreenPresenter provideCommentsScreenPresenter(){
        return commentsScreenPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AntisocialNetworkApp.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);
        progressBar.setIndeterminate(true);
        Post post = Parcels.unwrap(getIntent().getParcelableExtra(MainActivity.POST_KEY));
        recyclerView.setAdapter(commentsAdapter);
        if (!commentsAdapter.checkPost(post)) {
            commentsScreenPresenter.showProgressBar();
            commentsAdapter.clearComments();
            commentsAdapter.setPost(post);
            commentsScreenPresenter.requestComments(post.getId());
        }

    }

    @Override
    public void setComments(List<Comment> comments) {
        commentsAdapter.setComments(comments);
    }

    @Override
    public void setProgressVisibility(boolean isVisible) {
        if (isVisible) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorPopup(String message) {
        Snackbar.make(recyclerView, "Error: " + message, Snackbar.LENGTH_LONG).show();
    }
}
