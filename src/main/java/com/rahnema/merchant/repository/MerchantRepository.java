package com.rahnema.merchant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rahnema.merchant.domain.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long>{

}
