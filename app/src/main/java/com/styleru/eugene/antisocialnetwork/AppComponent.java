package com.styleru.eugene.antisocialnetwork;

import android.content.Context;

import com.styleru.eugene.antisocialnetwork.modules.NetworkModule;
import com.styleru.eugene.antisocialnetwork.presentation.commentsscreen.CommentsActivity;
import com.styleru.eugene.antisocialnetwork.presentation.createpost.CreatePostActivity;
import com.styleru.eugene.antisocialnetwork.presentation.mainscreen.MainActivity;


import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(CommentsActivity commentsActivity);
    void inject(CreatePostActivity createPostActivity);

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder context(Context context);
    }

}
