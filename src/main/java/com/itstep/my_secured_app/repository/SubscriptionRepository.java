package com.itstep.my_secured_app.repository;

import com.itstep.my_secured_app.model.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    Subscription findByUserIdAndEventId(int userId, int eventId);
}
