package com.rahnema.merchant.rest;

import java.net.URI;
import java.net.URISyntaxException;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahnema.merchant.domain.Transaction;
import com.rahnema.merchant.service.PurchaseService;

@RestController
@RequestMapping("/api")
public class PurchaseResource {

	private final Logger log = LoggerFactory.getLogger(PurchaseResource.class);

	private final PurchaseService purchaseService;

	public PurchaseResource(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
	@PostMapping("/purchase/{tel}")
	public ResponseEntity<Transaction> purchase(@PathVariable String tel, @RequestBody Transaction transaction)
			throws URISyntaxException, JSONException {
		log.debug("REST request for decreseReqAmount : {}", tel);
		Transaction tr = purchaseService.purchase(transaction.getMerchant(), transaction.getCustomerTel(),
				transaction.getAmount());
		
		return ResponseEntity.ok(tr);
	}
}
