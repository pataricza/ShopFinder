package com.finder.shop.repository;

import org.springframework.data.repository.CrudRepository;

import com.finder.shop.model.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
