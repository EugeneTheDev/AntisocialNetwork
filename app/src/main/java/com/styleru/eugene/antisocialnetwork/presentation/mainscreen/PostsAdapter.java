package com.styleru.eugene.antisocialnetwork.presentation.mainscreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.styleru.eugene.antisocialnetwork.R;
import com.styleru.eugene.antisocialnetwork.data.repository.SocNetworkRepository;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;

@Singleton
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private List<Post> posts;
    private MainScreenPresenter mainScreenPresenter;
    private final int VISIBILITY_THRESHOLD = 5;

    @Inject
    public PostsAdapter(MainScreenPresenter mainScreenPresenter) {
        this.posts = new ArrayList<>();
        this.mainScreenPresenter = mainScreenPresenter;

    }

    public void addPost(Post newPost){
        if (newPost.getId() == posts.get(posts.size() - 1).getId() - 1) {
            posts.add(newPost);
            notifyItemInserted(posts.size() - 1);
        } else if (newPost.getId() > SocNetworkRepository.MAX_ID){
            posts.add(0, newPost);
            notifyDataSetChanged();
        }

    }

    public void fillOnStart(Post post, int goalSize){
        posts.add(post);
        if (posts.size() == goalSize){
            Collections.sort(posts, (el1, el2)->el2.getId() - el1.getId());
            mainScreenPresenter.hideProgressBar();
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_post,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder viewHolder, int i) {
        Post post = posts.get(i);
        User user = post.getUser();
        viewHolder.authorName.setText(String.format("%s (%s)", user.getName(), user.getUsername()));
        viewHolder.authorEmail.setText(user.getEmail());
        viewHolder.postTitle.setText(post.getTitle());
        viewHolder.postText.setText(post.getBody());
        viewHolder.itemView.setOnClickListener(v->mainScreenPresenter.viewPostComments(post));

        if (posts.size() - i - 1 <= VISIBILITY_THRESHOLD)
            mainScreenPresenter.requestPost(posts.get(i).getId()-1);


    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.post_author_name)
        TextView authorName;

        @BindView(R.id.post_author_email)
        TextView authorEmail;

        @BindView(R.id.post_title)
        TextView postTitle;

        @BindView(R.id.post_text)
        TextView postText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
