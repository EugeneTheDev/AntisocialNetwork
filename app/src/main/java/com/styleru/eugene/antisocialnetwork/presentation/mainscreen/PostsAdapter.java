package com.styleru.eugene.antisocialnetwork.presentation.mainscreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.styleru.eugene.antisocialnetwork.R;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private List<Post> posts;
    private PostClick postClick;

    PostsAdapter(PostClick postClick) {
        this.posts = new ArrayList<>();
        this.postClick = postClick;

    }

    void addPosts(List<Post> posts){
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    void addPost(Post post){
        posts.add(0, post);
        notifyItemInserted(0);
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
        viewHolder.postTitle.setText(post.title);
        viewHolder.postText.setText(post.body);
        viewHolder.itemView.setOnClickListener(v -> postClick.onPostClicked(post));

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

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
