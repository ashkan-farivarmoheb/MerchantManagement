package com.rahnema.merchant.service;

import org.codehaus.jettison.json.JSONException;

import com.rahnema.merchant.domain.Merchant;
import com.rahnema.merchant.domain.Transaction;

public interface PurchaseService {

	Transaction purchase(Merchant merchant, String telCustomer, Integer amount) throws JSONException;
}
