package com.project.web.data;

import java.util.List;

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
}

