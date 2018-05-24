package ren.vic.domain.interactor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ren.vic.domain.executor.PostExecutionThread;
import ren.vic.domain.executor.ThreadExecutor;
import ren.vic.domain.repository.MessageRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class MessageGetWelcomeTest {

    private static final String FAKE_NAME = "fake_name";

    private MessageGetWelcome messageGetWelcome;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Mock
    private MessageRepository mockMessageRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        messageGetWelcome = new MessageGetWelcome(mockThreadExecutor, mockPostExecutionThread, mockMessageRepository);
    }

    @Test
    public void testGetWelcomeMessageUseCaseObservableHappyCase() {
        messageGetWelcome.buildUseCaseObservable(MessageGetWelcome.Param.forName(FAKE_NAME));

        verify(mockMessageRepository).welcomeMessage(FAKE_NAME);
        verifyNoMoreInteractions(mockMessageRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }

    @Test
    public void testShouldFailWhenNoOrEmptyParameters() {
        expectedException.expect(NullPointerException.class);
        messageGetWelcome.buildUseCaseObservable(null);
    }
}
