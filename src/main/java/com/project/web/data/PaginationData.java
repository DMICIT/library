package com.project.web.data;

import java.util.List;
import java.util.Objects;

public class PaginationData<T> {

    private List<T> entityList;
    private int totalAmount;

    public PaginationData() {
    }

    public PaginationData(List<T> list, int totalPagesAmount) {
        this.entityList = list;
        this.totalAmount = totalPagesAmount;
    }

    public List<T> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginationData<?> that = (PaginationData<?>) o;
        return totalAmount == that.totalAmount && Objects.equals(entityList, that.entityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityList, totalAmount);
    }

    @Override
    public String toString() {
        return "PaginationData{" +
                "entityList=" + entityList +
                ", totalAmount=" + totalAmount +
                '}';
    }
}

