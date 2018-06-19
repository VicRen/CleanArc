package ren.vic.data.repository;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import ren.vic.data.R;
import ren.vic.data.web.RetrofitInstance;
import ren.vic.data.web.WeatherApi;
import ren.vic.domain.entity.Message;
import ren.vic.domain.executor.SDKExecutionThread;
import ren.vic.domain.executor.ThreadExecutor;
import ren.vic.domain.repository.MessageRepository;

public class MessageDataRepository implements MessageRepository {

    private final Context context;
    private final WeatherApi weatherApi;
    private final SDKExecutionThread sdkExecutionThread;
    private final ThreadExecutor threadExecutor;

    @Inject
    MessageDataRepository(Context context, RetrofitInstance retrofitInstance,
                          SDKExecutionThread sdkExecutionThread, ThreadExecutor threadExecutor) {
        this.context = context;
        this.weatherApi = retrofitInstance.getWeatherApi();
        this.sdkExecutionThread = sdkExecutionThread;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public Observable<Message> welcomeMessage(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("sign", "kkFss8dsyjjNBhHqKMTw8Q==");
        map.put("username", "HE1712160911301054");
        map.put("location", "ningbo");
        map.put("t", "1515815034");
        return weatherApi.getCurrentWeather(map)
                .flatMap(weatherResponse -> Observable.create((ObservableOnSubscribe<Message>) e -> {
                    log("weatherResponse1");
                    Message message = new Message();
                    message.type = 0;
                    if (weatherResponse.isSuccess()) {
                        message.content = String.format(context.getString(R.string.welcome_note), name, weatherResponse.getWeatherNow().getCondTxt());
                    } else {
                        message.content = context.getString(R.string.error_note);
                    }
                    e.onNext(message);
                    e.onComplete();
                }))
                .subscribeOn(sdkExecutionThread.getScheduler())
                .unsubscribeOn(sdkExecutionThread.getScheduler())
                .observeOn(Schedulers.from(threadExecutor))
                .doOnNext(weatherResponse -> log("weatherResponse2"));
    }

    private void log(String msg) {
        System.out.println("Thread: " + Thread.currentThread().toString() + " message: "+ msg);
    }
}
