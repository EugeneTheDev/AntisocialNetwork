package com.styleru.eugene.antisocialnetwork.presentation.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.styleru.eugene.antisocialnetwork.AntisocialNetworkApp;
import com.styleru.eugene.antisocialnetwork.R;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.presentation.commentsscreen.CommentsActivity;
import com.styleru.eugene.antisocialnetwork.presentation.createpost.CreatePostActivity;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    public static final String POST_KEY = "post";

    public static final int REQUEST_CODE = 0;

    @BindView(R.id.posts_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.posts_loader)
    ProgressBar progressBar;

    @BindView(R.id.btn_new_post)
    FloatingActionButton btnNewPost;

    @Inject
    @InjectPresenter
    MainScreenPresenter mainScreenPresenter;

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
        postsAdapter = new PostsAdapter(mainScreenPresenter::onPostClicked);
        recyclerView.setAdapter(postsAdapter);
        mainScreenPresenter.setErrorMessage(getResources().getString(R.string.internet_error_message));
        mainScreenPresenter.onStart();
    }

    @OnClick(R.id.btn_new_post)
    void onClickNewPost(){
        startActivityForResult(new Intent(getApplicationContext(), CreatePostActivity.class), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            addPost(Parcels.unwrap(data.getParcelableExtra(POST_KEY)));
        }
    }

    @Override
    public void addPost(Post newPost){
        postsAdapter.addPost(newPost);
    }

    @Override
    public void setProgressVisibility(boolean isVisible) {
        if (isVisible) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showPostComments(Post post) {
        Intent intent = new Intent(getApplicationContext(), CommentsActivity.class);
        intent.putExtra(POST_KEY, Parcels.wrap(post));
        startActivity(intent);
    }

    @Override
    public void addPosts(List<Post> posts) {
        postsAdapter.addPosts(posts);
    }

    @Override
    public void showErrorPopup(String message) {
        Snackbar.make(recyclerView, "Error: " + message, Snackbar.LENGTH_LONG).show();
    }
}
