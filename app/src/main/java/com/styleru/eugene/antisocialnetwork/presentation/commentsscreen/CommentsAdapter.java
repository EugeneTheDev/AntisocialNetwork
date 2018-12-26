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

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Comment> comments;
    private Post post;
    private CommentsScreenPresenter commentsScreenPresenter;
    private final int VIEW_TYPE_POST = 0, VIEW_TYPE_COMMENT = 1;

    @Inject
    public CommentsAdapter(CommentsScreenPresenter commentsScreenPresenter) {
        comments = new ArrayList<>();
        this.commentsScreenPresenter = commentsScreenPresenter;
    }

    public void setComments(List<Comment> comments){
        this.comments = comments;
        commentsScreenPresenter.hideProgressBar();
        notifyDataSetChanged();
    }

    public void clearComments(){
        comments.clear();
    }

    public void setPost(Post post){
        this.post = post;
    }

    public boolean checkPost(Post post){
        return this.post != null && this.post.getId() == post.getId();
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
                        .inflate(R.layout.item_post,viewGroup,false);
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
                User user = post.getUser();
                postViewHolder.authorName.setText(String.format("%s (%s)", user.getName(), user.getUsername()));
                postViewHolder.authorEmail.setText(user.getEmail());
                postViewHolder.postTitle.setText(post.getTitle());
                postViewHolder.postText.setText(post.getBody());
                break;

            case VIEW_TYPE_COMMENT:
                CommentViewHolder commentViewHolder = (CommentViewHolder) viewHolder;
                Comment comment = comments.get(i - 1);
                commentViewHolder.authorName.setText(comment.getName());
                commentViewHolder.authorEmail.setText(comment.getEmail());
                commentViewHolder.text.setText(comment.getBody());
                break;
        }

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView authorName, authorEmail, postTitle, postText;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            authorName = itemView.findViewById(R.id.post_author_name);
            authorEmail = itemView.findViewById(R.id.post_author_email);
            postTitle = itemView.findViewById(R.id.post_title);
            postText = itemView.findViewById(R.id.post_text);
        }
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        private TextView authorName, authorEmail, text;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            authorName = itemView.findViewById(R.id.comment_author_name);
            authorEmail = itemView.findViewById(R.id.comment_author_email);
            text = itemView.findViewById(R.id.comment_text);
        }
    }

}
