package in.flexmoney.assignment.presentation.presenter;

import javax.inject.Inject;

import in.flexmoney.assignment.domain.entity.CardDetailsResponseEntity;
import in.flexmoney.assignment.domain.entity.CardEntity;
import in.flexmoney.assignment.domain.interactor.SubmitDetailsUseCase;
import in.flexmoney.assignment.presentation.view.BaseView;
import in.flexmoney.assignment.presentation.view.SubmitDetailsView;

public class SubmitDetailsPresenter extends BasePresenter implements Presenter {

    private SubmitDetailsUseCase submitDetailsUseCase;
    SubmitDetailsView submitDetailsView;

    @Inject
    public SubmitDetailsPresenter(SubmitDetailsUseCase submitDetailsUseCase) {
        super(submitDetailsUseCase);
        this.submitDetailsUseCase = submitDetailsUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.submitDetailsView = (SubmitDetailsView) view;
    }

    @Override
    public void destroy() {
        super.destroy();
        this.submitDetailsView = null;
    }

    public void submitDetails(CardEntity card) {
        this.showLoader();
        this.submitDetailsUseCase.setParams(card);
        this.submitDetailsUseCase.execute(new SubmitDetailsSubscriber());
    }

    protected class SubmitDetailsSubscriber extends BaseSubscriber<CardDetailsResponseEntity> {

        @Override
        public void onNext(CardDetailsResponseEntity cardDetails) {
            SubmitDetailsPresenter.this.hideLoader();
            SubmitDetailsPresenter.this.submitDetailsView.viewDetails(cardDetails);
        }

    }
}
