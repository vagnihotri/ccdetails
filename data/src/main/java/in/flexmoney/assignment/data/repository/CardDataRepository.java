package in.flexmoney.assignment.data.repository;

import java.util.HashMap;

import javax.inject.Inject;

import in.flexmoney.assignment.data.net.RestApi;
import in.flexmoney.assignment.domain.entity.CardDetailsResponseEntity;
import in.flexmoney.assignment.domain.entity.CardEntity;
import in.flexmoney.assignment.domain.repository.CardRepository;
import io.reactivex.Observable;

/**
 * Created by agni on 09/04/18.
 */

public class CardDataRepository implements CardRepository {

    private final RestApi restApi;

    @Inject
    public CardDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<CardDetailsResponseEntity> submitDetails(CardEntity card) {
        return this.restApi.submitDetails(card)
                .map(cardDetailsResponse -> {
                    //handleResponseError(cardDetailsResponse);
                    CardDetailsResponseEntity cardDetails = null;
                    if(cardDetailsResponse.isSuccessful()) {
                        cardDetails = cardDetailsResponse.body();
                        if (!cardDetails.isSuccess()) {
                            cardDetails.setErrorMessage((String) cardDetails.getData());
                        } else {
                            HashMap<String, Object> detailsMap = (HashMap) cardDetails.getData();
                            cardDetails.setName((String) detailsMap.get("name"));
                            cardDetails.setRequestId((Integer) detailsMap.get("requestId"));
                            cardDetails.setRequestTimestamp((Long) detailsMap.get("requestDate"));
                        }
                    } else {
                        String Json = cardDetailsResponse.errorBody().string();
                    }
                    return cardDetails;
                });
    }
}
