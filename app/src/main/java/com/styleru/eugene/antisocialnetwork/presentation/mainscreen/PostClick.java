package com.styleru.eugene.antisocialnetwork.presentation.mainscreen;

import com.styleru.eugene.antisocialnetwork.domain.entity.Post;

@FunctionalInterface
public interface PostClick {
    void onPostClicked(Post post);
}
