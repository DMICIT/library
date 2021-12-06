package com.project.web.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidationData {
    private boolean validationResult;
    private List<String> errorCodes = new ArrayList<>();

    public ValidationData() {
    }

    public ValidationData(boolean validationResult) {
        this.validationResult = validationResult;
    }

    public boolean isValidationResult() {
        return validationResult;
    }

    public void setValidationResult(boolean validationResult) {
        this.validationResult = validationResult;
    }

    public List<String> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public void addErrorCode(String code){
        this.errorCodes.add(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidationData that = (ValidationData) o;
        return validationResult == that.validationResult && Objects.equals(errorCodes, that.errorCodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(validationResult, errorCodes);
    }

    @Override
    public String toString() {
        return "ValidationData{" +
                "validationResult=" + validationResult +
                ", errorCodes=" + errorCodes +
                '}';
    }
}
