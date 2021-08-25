package com.itstep.my_secured_app.repository;

import com.itstep.my_secured_app.model.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<ConfirmationToken, Integer> {
    ConfirmationToken findByValue(String value);
}
