package ren.vic.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import io.reactivex.Observable;
import ren.vic.domain.entity.Message;
import ren.vic.domain.executor.PostExecutionThread;
import ren.vic.domain.executor.ThreadExecutor;
import ren.vic.domain.repository.MessageRepository;

public class MessageGetWelcome extends UseCase<Message, MessageGetWelcome.Param> {

    private final MessageRepository messageRepository;

    @Inject
    MessageGetWelcome(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                      MessageRepository messageRepository) {
        super(threadExecutor, postExecutionThread);
        this.messageRepository = messageRepository;
    }

    @Override
    Observable<Message> buildUseCaseObservable(Param param) {
        Preconditions.checkNotNull(param);
        return messageRepository.welcomeMessage(param.name);
    }

    public static final class Param {

        private final String name;
        private Param(String name) {
            this.name = name;
        }

        public static Param forName(String name) {
            return new Param(name);
        }
    }
}
