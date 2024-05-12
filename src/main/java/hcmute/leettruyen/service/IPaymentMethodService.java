package hcmute.leettruyen.service;

import hcmute.leettruyen.response.PaymentMethodResponse;
import hcmute.leettruyen.service.implement.PaymentMethodImpl;

import java.io.UnsupportedEncodingException;

public interface IPaymentMethodService {

    PaymentMethodResponse createPayment(int amount) throws UnsupportedEncodingException;

}
