package com.styleru.eugene.antisocialnetwork.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.styleru.eugene.antisocialnetwork.data.api.SocNetworkApi;
import com.styleru.eugene.antisocialnetwork.data.repository.SocNetworkRepository;
import com.styleru.eugene.antisocialnetwork.domain.repository.ISocNetworkRepository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetworkModule {

    @Provides
    ISocNetworkRepository provideISocNetworkRepository(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SocNetworkApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return new SocNetworkRepository(retrofit.create(SocNetworkApi.class));
    }
}
