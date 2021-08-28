package com.itstep.my_secured_app.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(SubscriptionKey.class)
@Table(name = "subscriptions")
@NoArgsConstructor
@Data
public class Subscription {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Id
    @Column(name = "event_id")
    private int eventId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date subscriptionDate;

    public Subscription(int userId, int eventId) {
        this.userId = userId;
        this.eventId = eventId;
        this.subscriptionDate = new Date();
    }
}
