package com.project.web.data;

import java.util.ArrayList;
import java.util.List;

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

}
