package in.flexmoney.assignment.domain.entity;

/**
 * Created by agni on 09/04/18.
 */

public class CardEntity {

    private String name;
    private String cardNo;
    private String cvv;
    private Integer expiryMonth;
    private Integer expiryYear;

    public CardEntity() {}

    public CardEntity(String name, String cardNo, String cvv, Integer expiryMonth, Integer expiryYear) {
        this.name = name;
        this.cardNo = cardNo;
        this.cvv = cvv;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }
}
