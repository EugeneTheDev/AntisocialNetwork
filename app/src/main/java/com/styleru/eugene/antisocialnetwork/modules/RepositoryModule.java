package com.styleru.eugene.antisocialnetwork.modules;

import com.styleru.eugene.antisocialnetwork.data.api.SocNetworkApi;
import com.styleru.eugene.antisocialnetwork.data.repository.SocNetworkRepository;
import com.styleru.eugene.antisocialnetwork.domain.repository.ISocNetworkRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {
    @Binds
    abstract ISocNetworkRepository bindSocNetworkRepository(SocNetworkRepository socNetworkRepository);
}
