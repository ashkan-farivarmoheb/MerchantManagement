package com.rahnema.merchant.service.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rahnema.merchant.api.ClientRestApi;
import com.rahnema.merchant.domain.Merchant;
import com.rahnema.merchant.domain.Transaction;
import com.rahnema.merchant.service.MerchantService;
import com.rahnema.merchant.service.PurchaseService;
import com.rahnema.merchant.service.TransactionService;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	private final MerchantService merchantService;
	private final TransactionService transactionService;
	private final ClientRestApi clientRestApi;
	
	private static final String SCHEDULER_URL = "/api/";

	private String getUrl() {
		return SCHEDULER_URL;
	}
	
	public PurchaseServiceImpl(MerchantService merchantService, TransactionService transactionService, ClientRestApi clientRestApi) {
		this.merchantService = merchantService;
		this.transactionService = transactionService;
		this.clientRestApi = clientRestApi;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public Transaction purchase(Merchant merchant, String telCustomer, Integer amount) throws JSONException {
		Map<HttpStatus, String> rsp = decreseReqAmount(telCustomer);
		if(rsp.containsKey(HttpStatus.CREATED)){
			JSONObject jsonObject = new JSONObject(rsp.get(HttpStatus.CREATED));
			Integer otp = jsonObject.getInt("otp");
			decreseSubAmount(telCustomer, otp, amount);
			increase(merchant.getTel(), amount);
			
			Transaction transaction= new Transaction(merchant, telCustomer, amount);
			transactionService.save(transaction);
			
			return transaction;
		}
		
		return null;
		
	}

	private Map<HttpStatus, String> increase(String tel, Integer amount) throws JSONException{
		
		JSONObject obj = new JSONObject();
		obj.put("amount", amount);		
		return clientRestApi.put(getUrl() + "increaseAmount/" + tel, obj.toString());
	}
	
	private Map<HttpStatus, String> decreseReqAmount(String tel){
		
		return clientRestApi.post(getUrl() + "decreseReqAmount/" + tel, null);
	}
	
	private Map<HttpStatus, String> decreseSubAmount(String tel, Integer opt, Integer amount) throws JSONException{
		
		JSONObject obj = new JSONObject();
		obj.put("amount", amount);		
		return clientRestApi.put(getUrl() + "decreseSubAmount/" + tel + "/" + opt, obj.toString());
	}
}
