package ren.vic.presentation.internal.di.modules;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import ren.vic.data.repository.MessageDataRepository;
import ren.vic.domain.repository.MessageRepository;

@Module
public abstract class MessageRepositoryModule {

    @Singleton
    @Binds
    abstract MessageRepository provideoMessageRepository(MessageDataRepository messageRepository);
}
