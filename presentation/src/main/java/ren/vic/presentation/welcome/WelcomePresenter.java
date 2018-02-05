package ren.vic.presentation.welcome;

import javax.inject.Inject;

import ren.vic.domain.entity.Message;
import ren.vic.domain.interactor.DefaultObserver;
import ren.vic.domain.interactor.MessageGetWelcome;

public class WelcomePresenter implements WelcomeContract.Presenter {

    private final MessageGetWelcome mMessageGetWelcome;
    private WelcomeContract.View mView;

    @Inject
    WelcomePresenter(MessageGetWelcome messageGetWelcome) {
        mMessageGetWelcome = messageGetWelcome;
    }

    @Override
    public void setView(WelcomeContract.View view) {
        mView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void showMessage(String name) {
        mMessageGetWelcome.execute(new WelcomeObserver(), MessageGetWelcome.Param.forName(name));
    }

    private final class WelcomeObserver extends DefaultObserver<Message> {

        @Override
        public void onNext(Message message) {
            mView.onShowMessage(message.content);
        }

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable e) {
        }
    }
}
