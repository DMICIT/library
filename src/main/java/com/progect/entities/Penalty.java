package com.progect.entities;

import java.util.Objects;

public class Penalty {
    private int id;
    private int orderId;
    private int userId;
    private int penaltyCost;

    public Penalty(int id, int orderId, int userId, int penaltyCost) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.penaltyCost = penaltyCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPenaltyCost() {
        return penaltyCost;
    }

    public void setPenaltyCost(int penaltyCost) {
        this.penaltyCost = penaltyCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Penalty penalty = (Penalty) o;
        return id == penalty.id && orderId == penalty.orderId && userId == penalty.userId && penaltyCost == penalty.penaltyCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, userId, penaltyCost);
    }

    @Override
    public String toString() {
        return "Penalty{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", penaltyCost=" + penaltyCost +
                '}';
    }
}
