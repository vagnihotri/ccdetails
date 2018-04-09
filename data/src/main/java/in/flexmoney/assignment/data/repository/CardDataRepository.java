package in.flexmoney.assignment.data.repository;

import java.util.Map;

import javax.inject.Inject;

import in.flexmoney.assignment.data.net.RestApi;
import in.flexmoney.assignment.domain.entity.CardDetailsResponseEntity;
import in.flexmoney.assignment.domain.entity.CardEntity;
import in.flexmoney.assignment.domain.repository.CardRepository;
import io.reactivex.Observable;

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
                    CardDetailsResponseEntity cardDetails = null;
                    if(cardDetailsResponse.isSuccessful()) {
                        cardDetails = cardDetailsResponse.body();
                        if (!cardDetails.isSuccess()) {
                            cardDetails.setErrorMessage((String) cardDetails.getData());
                        } else {
                            Map<String, Object> detailsMap = (Map) cardDetails.getData();
                            cardDetails.setName((String) (detailsMap.get("name")));
                            Object requestId = detailsMap.get("requestId");
                            cardDetails.setRequestId(parse(requestId));
                            Object requestDate = detailsMap.get("requestDate");
                            cardDetails.setRequestTimestamp(parse(requestDate));
                        }
                    } else {
                       cardDetails = new CardDetailsResponseEntity();
                       cardDetails.setSuccess(false);
                       cardDetails.setErrorMessage("Cannot fetch card details at this time");
                    }
                    return cardDetails;
                });
    }

    private Long parse(Object value) {
        Long longValue = null;
        if(value instanceof Integer) {
            Integer val = (Integer) value;
            longValue = val.longValue();
        } else if(value instanceof Long) {
            longValue = (Long) value;
        } else if(value instanceof Double) {
            Double val = (Double) value;
            longValue = val.longValue();
        } else if(value instanceof String) {
            String val = (String) value;
            longValue = Long.valueOf(val);
        }
        return longValue;
    }
}
