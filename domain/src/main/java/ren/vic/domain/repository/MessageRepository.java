package ren.vic.domain.repository;

import io.reactivex.Observable;
import ren.vic.domain.entity.Message;

public interface MessageRepository {

    Observable<Message> welcomeMessage(String name);
}
