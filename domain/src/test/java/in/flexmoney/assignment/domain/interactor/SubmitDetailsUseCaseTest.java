package in.flexmoney.assignment.domain.interactor;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import in.flexmoney.assignment.domain.entity.CardDetailsResponseEntity;
import in.flexmoney.assignment.domain.entity.CardEntity;
import in.flexmoney.assignment.domain.executor.PostExecutionThread;
import in.flexmoney.assignment.domain.executor.ThreadExecutor;
import in.flexmoney.assignment.domain.repository.CardRepository;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class SubmitDetailsUseCaseTest {

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private CardRepository mockCardRepository;

    @Before
    public void setup() { MockitoAnnotations.initMocks(this); }

    @Test
    public void testGetNotesUseCaseSuccess() {
        SubmitDetailsUseCase submitDetailsUseCase = new SubmitDetailsUseCase(mockThreadExecutor,
                mockPostExecutionThread, mockCardRepository);
        TestObserver<CardDetailsResponseEntity> testObserver = new TestObserver<>();
        CardDetailsResponseEntity cardDetails = new CardDetailsResponseEntity();
        cardDetails.setName("Test");
        given(mockCardRepository.submitDetails(any(CardEntity.class)))
                .willReturn(Observable.just(cardDetails));

        submitDetailsUseCase.buildUseCaseObservable().subscribe(testObserver);

        Assert.assertEquals(cardDetails.getName(),
                ((CardDetailsResponseEntity)(testObserver.getEvents().get(0)).get(0)).getName());

        verify(mockCardRepository).submitDetails(null);
        verifyNoMoreInteractions(mockCardRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
