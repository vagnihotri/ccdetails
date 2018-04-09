package in.flexmoney.assignment.presentation.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import in.flexmoney.assignment.data.net.error.RestApiErrorException;
import in.flexmoney.assignment.domain.entity.CardDetailsResponseEntity;
import in.flexmoney.assignment.domain.entity.CardEntity;
import in.flexmoney.assignment.domain.interactor.SubmitDetailsUseCase;
import in.flexmoney.assignment.presentation.view.SubmitDetailsView;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class SubmitDetailsPresenterTest {

    @Mock SubmitDetailsUseCase mockSubmitDetailsUseCase;
    @Mock SubmitDetailsView mockSubmitDetailsView;

    private SubmitDetailsPresenter submitDetailsPresenter;
    private SubmitDetailsPresenter.SubmitDetailsSubscriber submitDetailsSubscriber;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.submitDetailsPresenter = new SubmitDetailsPresenter(this.mockSubmitDetailsUseCase);
        this.submitDetailsPresenter.initWithView(this.mockSubmitDetailsView);
        this.submitDetailsSubscriber = this.submitDetailsPresenter.new SubmitDetailsSubscriber();
    }

    @Test
    public void testDestroy() {

        this.submitDetailsPresenter.destroy();

        verify(this.mockSubmitDetailsUseCase).unsubscribe();
        assertNull(this.submitDetailsPresenter.submitDetailsView);
        assertNull(this.submitDetailsPresenter.view);
    }

    @Test
    public void testSubmitDetails() throws Exception {

        this.submitDetailsPresenter.submitDetails(new CardEntity("Test", "1234567890123456", "123", 12, 2021));

        verify(this.mockSubmitDetailsView).showLoader();
        verify(this.mockSubmitDetailsUseCase).setParams(any(CardEntity.class));
        verify(this.mockSubmitDetailsUseCase).execute(any(BasePresenter.BaseSubscriber.class));
    }

    @Test
    public void testSubscriberOnCompleted() {

        this.submitDetailsSubscriber.onComplete();

        verify(this.mockSubmitDetailsView).hideLoader();
    }

    @Test
    public void testSubscriberOnError() {

        this.submitDetailsSubscriber.onError(new RestApiErrorException("Error message", 500));

        verify(this.mockSubmitDetailsView).hideLoader();
        verify(this.mockSubmitDetailsView).handleError(any(Throwable.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSubscriberOnNext() {
        CardDetailsResponseEntity cardDetailsResponseEntity = new CardDetailsResponseEntity();
        this.submitDetailsSubscriber.onNext(cardDetailsResponseEntity);

        verify(this.mockSubmitDetailsView).hideLoader();
        verify(this.mockSubmitDetailsView).viewDetails(cardDetailsResponseEntity);
    }

}
