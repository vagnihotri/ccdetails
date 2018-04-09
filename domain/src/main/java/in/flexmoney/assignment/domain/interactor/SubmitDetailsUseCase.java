package in.flexmoney.assignment.domain.interactor;

import javax.inject.Inject;

import in.flexmoney.assignment.domain.entity.CardDetailsResponseEntity;
import in.flexmoney.assignment.domain.entity.CardEntity;
import in.flexmoney.assignment.domain.executor.PostExecutionThread;
import in.flexmoney.assignment.domain.executor.ThreadExecutor;
import in.flexmoney.assignment.domain.repository.CardRepository;
import io.reactivex.Observable;

/**
 * Created by agni on 09/04/18.
 */

public class SubmitDetailsUseCase extends UseCase<CardDetailsResponseEntity> {

    private CardRepository cardRepository;
    private CardEntity cardEntity;

    @Inject
    public SubmitDetailsUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                                CardRepository cardRepository) {
        super(threadExecutor, postExecutionThread);
        this.cardRepository = cardRepository;
    }

    public void setParams(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }

    @Override
    protected Observable<CardDetailsResponseEntity> buildUseCaseObservable() {
        return this.cardRepository.submitDetails(cardEntity);
    }
}
