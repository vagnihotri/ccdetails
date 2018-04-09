package in.flexmoney.assignment.domain.repository;

import in.flexmoney.assignment.domain.entity.CardDetailsResponseEntity;
import in.flexmoney.assignment.domain.entity.CardEntity;
import io.reactivex.Observable;

public interface CardRepository {
    Observable<CardDetailsResponseEntity> submitDetails(CardEntity card);
}
