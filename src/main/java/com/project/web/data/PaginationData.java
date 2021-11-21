package com.project.web.data;

import java.util.List;

public class PaginationData<T> {

    private List<T> list;

    private int totalPagesAmount;

    public PaginationData() {
    }

    public PaginationData(List<T> list, int totalPagesAmount) {
        this.list = list;
        this.totalPagesAmount = totalPagesAmount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalPagesAmount() {
        return totalPagesAmount;
    }

    public void setTotalPagesAmount(int totalPagesAmount) {
        this.totalPagesAmount = totalPagesAmount;
    }
}

