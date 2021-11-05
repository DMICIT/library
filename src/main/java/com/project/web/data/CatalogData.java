package com.project.web.data;

public class CatalogData {
    private int totalQuantity;
    private int availableQuantity;
    private int checkedOutQuantity;

    public CatalogData(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public CatalogData(int totalQuantity, int availableQuantity, int checkedOutQuantity) {
        this.totalQuantity = totalQuantity;
        this.availableQuantity = availableQuantity;
        this.checkedOutQuantity = checkedOutQuantity;
    }

    public CatalogData() {

    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getCheckedOutQuantity() {
        return checkedOutQuantity;
    }

    public void setCheckedOutQuantity(int checkedOutQuantity) {
        this.checkedOutQuantity = checkedOutQuantity;
    }
}
