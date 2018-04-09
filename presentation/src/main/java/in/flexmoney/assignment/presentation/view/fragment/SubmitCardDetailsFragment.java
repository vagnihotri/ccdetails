package in.flexmoney.assignment.presentation.view.fragment;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import in.flexmoney.assignment.domain.entity.CardDetailsResponseEntity;
import in.flexmoney.assignment.domain.entity.CardEntity;
import in.flexmoney.assignment.presentation.R;
import in.flexmoney.assignment.presentation.presenter.BasePresenter;
import in.flexmoney.assignment.presentation.presenter.SubmitDetailsPresenter;
import in.flexmoney.assignment.presentation.view.SubmitDetailsView;

public class SubmitCardDetailsFragment extends BaseFragment implements SubmitDetailsView {

    @Inject
    SubmitDetailsPresenter submitDetailsPresenter;

    @BindView(R.id.tiet_name) TextInputEditText nameEditText;
    @BindView(R.id.tiet_card_number) TextInputEditText cardNumberEditText;
    @BindView(R.id.tiet_cvv) TextInputEditText cvvEditText;
    @BindView(R.id.tiet_expiry_month) TextInputEditText expiryMonthEditText;
    @BindView(R.id.tiet_expiry_year) TextInputEditText expiryYearEditText;

    @BindView(R.id.til_name) TextInputLayout nameInputLayout;
    @BindView(R.id.til_card_number) TextInputLayout cardNumberInputLayout;
    @BindView(R.id.til_cvv) TextInputLayout cvvInputLayout;
    @BindView(R.id.til_expiry_month) TextInputLayout expiryMonthInputLayout;
    @BindView(R.id.til_expiry_year) TextInputLayout expiryYearInputLayout;

    @Override
    protected void callInjection() {
        this.getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_card_details;
    }

    @Override
    public BasePresenter presenter() {
        return this.submitDetailsPresenter;
    }

    @OnClick(R.id.btn_submit)
    public void submitButtonPressed() {
        CardEntity card = validateCardDetails();
        if(card != null) {
            this.submitDetailsPresenter.submitDetails(card);
            this.hideKeyboard();
        }
    }

    private CardEntity validateCardDetails() {
        CardEntity cardEntity = null;
        boolean isValid = true;
        String errorString = "Some fields are not proper:";

        String name = nameEditText.getText().toString();
        String cardNo = cardNumberEditText.getText().toString();
        String cvv = cvvEditText.getText().toString();
        Integer expiryMonth = null, expiryYear = null;

        if(name.isEmpty()) {
            isValid = false;
            errorString += "\n" + getString(R.string.empty_name);
        }

        if(cardNo.isEmpty()) {
            isValid = false;
            errorString += "\n" + getString(R.string.empty_card_number);
        }

        if(cvv.isEmpty()) {
            isValid = false;
            errorString += "\n" + getString(R.string.empty_cvv);
        }

        if(expiryMonthEditText.getText().toString().isEmpty()) {
            isValid = false;
            errorString += "\n" + getString(R.string.empty_expiry_month);
        } else {
            expiryMonth = Integer.valueOf(expiryMonthEditText.getText().toString());
        }

        if(expiryYearEditText.getText().toString().isEmpty()) {
            isValid = false;
            errorString += "\n" + getString(R.string.empty_expiry_year);
        } else {
            expiryYear = Integer.valueOf(expiryYearEditText.getText().toString());
        }

        if(cardNo.length() > 0 && cardNo.length() != 16) {
            isValid = false;
            errorString += "\n" + getString(R.string.invalid_card_number);
        }

        if(cvv.length() > 0 && cvv.length() != 3) {
            isValid = false;
            errorString += "\n" + getString(R.string.invalid_cvv);
        }
        if(expiryMonth != null && expiryMonth > 12) {
            isValid = false;
            errorString += "\n" + getString(R.string.invalid_expiry_month);
        }
        if(expiryYear != null && (expiryYear < 2018 || expiryYear > 2029)) {
            isValid = false;
            errorString += "\n" + getString(R.string.invalid_expiry_year);
        }

        if(isValid) {
            cardEntity = new CardEntity(name, cardNo, cvv, expiryMonth,expiryYear);
        } else {
            showAlert("Error!", errorString);
        }
        return cardEntity;
    }

    @Override
    public void viewDetails(CardDetailsResponseEntity cardDetails) {
        String title, message;
        if(!cardDetails.isSuccess()) {
            title = "Error!";
            message = cardDetails.getErrorMessage();
        } else {
            Date date = new Date(cardDetails.getRequestTimestamp());
            SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy HH:mm Z", Locale.US);
            String dateString = format.format(date);
            title = "Card Details - \n";
            message = "Request Id: " + cardDetails.getRequestId() + "\n" +
                      "Name: " + cardDetails.getName() + "\n" +
                      "Request Date: " + dateString;
        }
        showAlert(title, message);
    }
}
