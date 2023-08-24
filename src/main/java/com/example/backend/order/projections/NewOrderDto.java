package com.example.backend.order.projections;

public class NewOrderDto {

    private Long clientId;
    private float value;

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setValue(float value) {
        this.value = value;
    }


    public Long getClientId() {
        return clientId;
    }

    public float getValue() {
        return value;
    }
}
