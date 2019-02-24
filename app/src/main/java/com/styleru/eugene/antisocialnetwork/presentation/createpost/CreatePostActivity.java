package com.styleru.eugene.antisocialnetwork.presentation.createpost;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.styleru.eugene.antisocialnetwork.AntisocialNetworkApp;
import com.styleru.eugene.antisocialnetwork.R;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.presentation.mainscreen.MainActivity;

import org.parceler.Parcels;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.ViewCollections;

public class CreatePostActivity extends MvpAppCompatActivity implements CreatePostView {

    @BindView(R.id.username_input_text)
    TextInputEditText usernameInput;

    @BindView(R.id.title_input_text)
    TextInputEditText titleInput;

    @BindView(R.id.body_input_text)
    TextInputEditText bodyInput;

    @BindView(R.id.new_posts_loader)
    ProgressBar progressBar;

    @BindView(R.id.btn_post)
    Button btnPost;

    @Inject
    @InjectPresenter
    CreatePostPresenter createPostPresenter;

    @ProvidePresenter
    CreatePostPresenter provideCreatePostPresenter(){
        return createPostPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AntisocialNetworkApp.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        ButterKnife.bind(this);
        createPostPresenter.setErrorMessages(getResources().getString(R.string.internet_error_message),
                getResources().getString(R.string.not_found_error_message));
        progressBar.setIndeterminate(true);
    }


    @Override
    public void result(Post post) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(MainActivity.POST_KEY, Parcels.wrap(post));
        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick(R.id.btn_post)
    void onClickPost(){
        createPostPresenter.onClickPost(usernameInput.getText().toString().trim(),
                titleInput.getText().toString().trim(), bodyInput.getText().toString().trim());
    }

    @Override
    public void replaceButtonWithLoader(boolean isButtonVisible) {
        if (isButtonVisible){
            progressBar.setVisibility(View.GONE);
            btnPost.setVisibility(View.VISIBLE);
        } else{
            progressBar.setVisibility(View.VISIBLE);
            btnPost.setVisibility(View.GONE);

        }
    }

    @Override
    public void showErrorPopup(String message) {
        Snackbar.make(usernameInput, "Error: " + message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setUsernameError(String error) {
        usernameInput.setError(error);
    }

    @Override
    public void setTitleError(String error) {
        titleInput.setError(error);
    }

    @Override
    public void setBodyError(String error) {
        bodyInput.setError(error);
    }

    @Override
    public void clearFields() {
        ViewCollections.run(Arrays.asList(usernameInput, titleInput, bodyInput),
                (view, index) -> view.setText(""));
    }
}
