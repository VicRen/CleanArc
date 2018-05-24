package ren.vic.data.repository;

import android.test.mock.MockContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import ren.vic.data.entity.WeatherResponse;
import ren.vic.data.web.RetrofitInstance;
import ren.vic.data.web.WeatherApi;
import ren.vic.domain.entity.Message;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageDataRepositoryTest {

    private static final String FAKE_NAME = "fake_name";
    private static final String FAKE_WEATHER = "fake_weather";

    private MessageDataRepository messageDataRepository;

    private TestDisposableObserver testDisposableObserver;

    @Mock
    private MockContext mockContext;

    @Mock
    private RetrofitInstance mockRetrofit;

    @Mock
    private WeatherApi mockWeatherApi;

    @Mock
    private WeatherResponse mockWeatherResponse;

    @Mock
    private WeatherResponse.WeatherNow mockWeatherNow;

    @Before
    public void setUp() {
        given(mockRetrofit.getWeatherApi()).willReturn(mockWeatherApi);
        given(mockContext.getString(anyInt())).willReturn("Hi %1$s, Welcome! It\\'s %2$s today.");
        messageDataRepository = new MessageDataRepository(mockContext, mockRetrofit);
        testDisposableObserver = new TestDisposableObserver();
    }

    @Test
    public void testGetWelComeMessageHappyCase() {
        given(mockWeatherApi.getCurrentWeather(anyMap())).willReturn(Observable.just(mockWeatherResponse));
        given(mockWeatherResponse.isSuccess()).willReturn(true);
        given(mockWeatherResponse.getWeatherNow()).willReturn(mockWeatherNow);
        when(mockWeatherNow.getCondTxt()).thenReturn(FAKE_WEATHER);
        messageDataRepository.welcomeMessage(FAKE_NAME).subscribeWith(testDisposableObserver);

        assertThat(testDisposableObserver.message, is(String.format("Hi %1$s, Welcome! It\\'s %2$s today.", FAKE_NAME, FAKE_WEATHER)));
    }

    private static class TestDisposableObserver extends DisposableObserver<Message> {
        private int valuesCount = 0;
        private String message;

        @Override public void onNext(Message value) {
            valuesCount++;
            message = value.content;
        }

        @Override public void onError(Throwable e) {
            // no-op by default.
        }

        @Override public void onComplete() {
            // no-op by default.
        }
    }
}
