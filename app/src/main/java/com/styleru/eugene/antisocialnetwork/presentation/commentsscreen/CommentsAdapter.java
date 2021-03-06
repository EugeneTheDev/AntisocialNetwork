package com.styleru.eugene.antisocialnetwork.presentation.commentsscreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.styleru.eugene.antisocialnetwork.R;
import com.styleru.eugene.antisocialnetwork.domain.entity.Comment;
import com.styleru.eugene.antisocialnetwork.domain.entity.Post;
import com.styleru.eugene.antisocialnetwork.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Comment> comments;
    private Post post;
    private final int VIEW_TYPE_POST = 0, VIEW_TYPE_COMMENT = 1;

    CommentsAdapter() {
        comments = new ArrayList<>();
    }

    void setComments(List<Comment> comments){
        this.comments = comments;
        notifyDataSetChanged();
    }

    public void setPost(Post post){
        this.post = post;
        notifyItemChanged(0);
    }

    @Override
    public int getItemViewType(int position) {
        return position > 0 ? VIEW_TYPE_COMMENT : VIEW_TYPE_POST;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_POST:
                View vPost = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_post_with_author,viewGroup,false);
                return new PostViewHolder(vPost);
            default:
                View vComment = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_comment,viewGroup,false);
                return new CommentViewHolder(vComment);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        switch (viewHolder.getItemViewType()) {
            case VIEW_TYPE_POST:
                PostViewHolder postViewHolder = (PostViewHolder) viewHolder;
                User user = post.user;
                postViewHolder.authorName.setText(String.format("%s (%s)", user.name, user.username));
                postViewHolder.authorEmail.setText(user.email);
                postViewHolder.postTitle.setText(post.title);
                postViewHolder.postText.setText(post.body);
                break;

            case VIEW_TYPE_COMMENT:
                CommentViewHolder commentViewHolder = (CommentViewHolder) viewHolder;
                Comment comment = comments.get(i - 1);
                commentViewHolder.authorName.setText(comment.name);
                commentViewHolder.authorEmail.setText(comment.email);
                commentViewHolder.text.setText(comment.body);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return post == null? 0 : comments.size() + 1;
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.post_author_name)
        TextView authorName;

        @BindView(R.id.post_author_email)
        TextView authorEmail;

        @BindView(R.id.post_with_author_title)
        TextView postTitle;

        @BindView(R.id.post_with_author_text)
        TextView postText;

        PostViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.comment_author_name)
        TextView authorName;

        @BindView(R.id.comment_author_email)
        TextView authorEmail;

        @BindView(R.id.comment_text)
        TextView text;

        CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
