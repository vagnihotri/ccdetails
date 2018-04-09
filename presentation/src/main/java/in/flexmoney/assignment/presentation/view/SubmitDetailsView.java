package in.flexmoney.assignment.presentation.view;

import in.flexmoney.assignment.domain.entity.CardDetailsResponseEntity;

/**
 * Created by agni on 09/04/18.
 */

public interface SubmitDetailsView extends BaseView {
    void viewDetails(CardDetailsResponseEntity cardDetails);
}
