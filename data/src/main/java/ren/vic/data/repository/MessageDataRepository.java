package ren.vic.data.repository;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observable;
import ren.vic.data.R;
import ren.vic.domain.entity.Message;
import ren.vic.domain.repository.MessageRepository;

public class MessageDataRepository implements MessageRepository {

    private final Context context;

    @Inject
    MessageDataRepository(Context context) {
        this.context = context;
    }

    @Override
    public Observable<Message> welcomeMessage(String name) {
        return Observable.create(e -> {
            Message message = new Message();
            message.type = 0;
            message.content = String.format(context.getString(R.string.welcome_note), name);
            e.onNext(message);
            e.onComplete();
        });
    }
}
