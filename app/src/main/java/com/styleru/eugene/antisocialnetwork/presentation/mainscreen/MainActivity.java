package com.styleru.eugene.antisocialnetwork.presentation.mainscreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.styleru.eugene.antisocialnetwork.AntisocialNetworkApp;
import com.styleru.eugene.antisocialnetwork.R;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.posts_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.posts_loader)
    ProgressBar progressBar;

    @BindView(R.id.btn_new_post)
    FloatingActionButton btnNewPost;

    @Inject
    @InjectPresenter
    MainScreenPresenter mainScreenPresenter;

    @Inject
    PostsAdapter postsAdapter;

    @ProvidePresenter
    MainScreenPresenter provideMainScreenPresenter(){
        return mainScreenPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AntisocialNetworkApp.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressBar.setIndeterminate(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && btnNewPost.getVisibility() == View.VISIBLE)
                    btnNewPost.hide();
                else if (dy < 0 && btnNewPost.getVisibility() != View.VISIBLE)
                    btnNewPost.show();
            }
        });
        recyclerView.setAdapter(postsAdapter);
        if (postsAdapter.getItemCount() == 0) mainScreenPresenter.fillOnStart();

    }

    @OnClick(R.id.btn_new_post)
    void onClickNewPost(){
    }

    @Override
    public void addNewPost(Post newPost){
        postsAdapter.addNewPost(newPost);
    }

    @Override
    public void setProgressVisibility(boolean isVisible) {
        if (isVisible) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void fillOnStart(Post post, int goalSize) {
        postsAdapter.fillOnStart(post, goalSize);
    }

    @Override
    public void showErrorPopup(String message) {
        Snackbar.make(recyclerView, "Error: " + message, Snackbar.LENGTH_LONG).show();

    }
}
