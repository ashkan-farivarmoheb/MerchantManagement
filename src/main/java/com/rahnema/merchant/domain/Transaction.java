package com.rahnema.merchant.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8102728979237391071L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Merchant merchant;
	
	@Column(name="customer_tel")
	private String customerTel;
	
	private Integer amount;
	
	private String refCode;
	
	private Date buyTime;
	
	public Transaction(){}
	
	public Transaction(Merchant merchant, String customerTel, Integer amount) {
		super();
		this.merchant = merchant;
		this.customerTel = customerTel;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	@PrePersist
	void preInsert() {
		this.buyTime = new Date();		
		this.refCode = generateRefCode();
	}
	
	private String generateRefCode(){
    	String code;
		do {
			code = org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(14);
		} while (!code.matches(".*(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*"));
		return code;
    }
}
