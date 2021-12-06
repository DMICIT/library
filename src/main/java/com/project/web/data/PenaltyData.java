package com.project.web.data;

import java.util.Objects;

public class PenaltyData {
    private int penaltyCost;

    public PenaltyData(int penaltyCost) {
        this.penaltyCost = penaltyCost;
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
        PenaltyData that = (PenaltyData) o;
        return penaltyCost == that.penaltyCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(penaltyCost);
    }

    @Override
    public String toString() {
        return "PenaltyData{" +
                "penaltyCost=" + penaltyCost +
                '}';
    }
}
