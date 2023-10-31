package com.jennifer.sbank.repositories;

import com.jennifer.sbank.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long>{
}
