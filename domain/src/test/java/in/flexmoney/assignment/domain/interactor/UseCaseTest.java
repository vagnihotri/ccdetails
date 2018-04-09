package in.flexmoney.assignment.domain.interactor;

import in.flexmoney.assignment.domain.executor.PostExecutionThread;
import in.flexmoney.assignment.domain.executor.ThreadExecutor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class UseCaseTest {

    private TestObserver<Integer> testObserver;
    private FakeUseCase fakeUseCase;

    @Mock private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.testObserver = new TestObserver<>();
        this.fakeUseCase = new FakeUseCase(new DefaultThreadExecutor(), mockPostExecutionThread);
    }

    @Test
    public void testUseCaseExecutionResult() {
        TestScheduler testScheduler = new TestScheduler();
        given(this.mockPostExecutionThread.getScheduler()).willReturn(testScheduler);

        this.fakeUseCase.execute(testObserver);
        testScheduler.triggerActions();

        this.testObserver.assertNoErrors();
        this.testObserver.assertResult(1, 2, 3);
    }

    @Test
    public void testUseCaseUnsubscription() {
        TestScheduler testScheduler = new TestScheduler();
        given(this.mockPostExecutionThread.getScheduler()).willReturn(testScheduler);

        this.fakeUseCase.execute(testObserver);
        assertThat(this.fakeUseCase.isUnsubscribed(), is(false));

        this.fakeUseCase.unsubscribe();
        assertThat(this.fakeUseCase.isUnsubscribed(), is(true));
    }

    private static class FakeUseCase extends UseCase<Integer> {

        protected FakeUseCase(ThreadExecutor threadExecutor,
                              PostExecutionThread postExecutionThread) {
            super(threadExecutor, postExecutionThread);
        }

        @Override protected Observable<Integer> buildUseCaseObservable() {
            return Observable.just(1, 2, 3);
        }

    }

    private class DefaultThreadExecutor implements ThreadExecutor {

        @Override
        public void execute(Runnable command) {
            command.run();
        }

    }


}
