package com.project.web.data;

import java.util.Objects;

public class CatalogData {
    private int totalQuantity;

    public CatalogData() {
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogData that = (CatalogData) o;
        return totalQuantity == that.totalQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalQuantity);
    }

    @Override
    public String toString() {
        return "CatalogData{" +
                "totalQuantity=" + totalQuantity +
                '}';
    }
}