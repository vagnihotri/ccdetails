package in.flexmoney.assignment.data.net;

import in.flexmoney.assignment.domain.entity.CardDetailsResponseEntity;
import in.flexmoney.assignment.domain.entity.CardEntity;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestApi {

    String URL_BASE = " https://staging.flexmoney.in";
    String CONTENT_TYPE = "application/json";
    String ORIGIN = "flexmoney.in";

    @POST("/app/dummy-card-details/submit")
    Observable<Response<CardDetailsResponseEntity>> submitDetails(@Body CardEntity cardDetails);

}
