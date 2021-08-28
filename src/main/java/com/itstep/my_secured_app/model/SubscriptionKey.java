package com.itstep.my_secured_app.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubscriptionKey implements Serializable {
    private int userId;
    private int eventId;
}
