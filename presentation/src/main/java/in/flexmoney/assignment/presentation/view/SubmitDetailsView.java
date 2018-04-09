package in.flexmoney.assignment.presentation.view;

import in.flexmoney.assignment.domain.entity.CardDetailsResponseEntity;

public interface SubmitDetailsView extends BaseView {
    void viewDetails(CardDetailsResponseEntity cardDetails);
}
