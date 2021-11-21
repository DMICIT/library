package com.project.services;


import com.project.forms.AdminAddUserForm;
import com.project.forms.AdminEditBookForm;
import com.project.forms.LoginForm;
import com.project.forms.RegistrationForm;
import com.project.web.data.ValidationData;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidatorService {
    private static final Logger LOG = Logger.getLogger(ValidatorService.class);

    private static Pattern patternNameValidation = Pattern.compile("[A-Za-zА-Яа-я ]*");
    private static Pattern patternEmailValidation = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static Pattern patternBookNameValidation = Pattern.compile("[A-Za-zА-Яа-я0-9_.,()!?' -]*");
    private static Pattern patternPhoneValidation = Pattern.compile("[0-9+]");
    private static Pattern patternReliaseDateValidation = Pattern.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
    private static Pattern patternPasswordValidation = Pattern.compile("^(?=.*?[A-Za-z0-9#?!@$%^&*-]){4,}$");
    private static Pattern patternBookEditionValidation = Pattern.compile("[A-Za-z0-9#?!@$%^&* -]*");
    private static Pattern patternQuantityValidation = Pattern.compile("[0-9]*");


    public  ValidationData validate (RegistrationForm form){

        ValidationData validationData = new ValidationData();
        validationData.setValidationResult(true);

       Matcher matcher = patternNameValidation.matcher(form.getName());
        if(!matcher.matches()) {
            validationData.addErrorCode("error.wrong.name");
        }
        matcher = patternEmailValidation.matcher(form.getEmail());
        if(!matcher.matches()) {
            validationData.addErrorCode("error.wrong.email");
        }
        if(!form.getSex().equalsIgnoreCase("man") && !form.getSex().equalsIgnoreCase("woman")){
            validationData.addErrorCode("error.wrong.gender");
        }
        matcher = patternPhoneValidation.matcher(form.getPhone());
        if(!matcher.matches()) {
            validationData.addErrorCode("error.wrong.phone");
        }
        if (!form.getPassword().equals(form.getConfirmPassword())){
           validationData.addErrorCode("error.equals.passwords");
        }

        if (!validationData.getErrorCodes().isEmpty()){
            validationData.setValidationResult(false);
        }
        return validationData;
    }
    public  boolean validate(LoginForm form) {
        boolean result = true;

        Matcher matcher = patternEmailValidation.matcher(form.getEmail());
        if (!matcher.matches()) {
            result = false;
        }
        return result;
    }

    public ValidationData validate(AdminAddUserForm form){

        ValidationData validationData = new ValidationData();
        validationData.setValidationResult(true);

        Matcher matcher = patternNameValidation.matcher(form.getName());
        if(!matcher.matches()) {
           validationData.addErrorCode("error.wrong.name");
        }
        matcher = patternEmailValidation.matcher(form.getEmail());
        if(!matcher.matches()) {
            validationData.addErrorCode("error.wrong.email");
        }
        if(!form.getSex().equalsIgnoreCase("man") && !form.getSex().equalsIgnoreCase("woman")){
            validationData.addErrorCode("error.wrong.gender");
        }
        matcher = patternPhoneValidation.matcher(form.getPhone());
        if(!matcher.matches()) {
            validationData.addErrorCode("error.wrong.phone");
        }
        matcher = patternPasswordValidation.matcher(form.getPassword());
        if(!matcher.matches()) {
            validationData.addErrorCode("error.wrong.password");
        }
        if (!validationData.getErrorCodes().isEmpty()){
            validationData.setValidationResult(false);
        }
        return validationData;
    }
    public ValidationData validate (AdminEditBookForm form){

        ValidationData validationData = new ValidationData();

        Matcher matcher = patternNameValidation.matcher(form.getAuthor());
        if(!matcher.matches()) {
            validationData.addErrorCode("error.wrong.name");
        }
        matcher = patternBookNameValidation.matcher(form.getBookName());
        if(!matcher.matches()) {
            validationData.addErrorCode("error.book.name");
        }
        matcher = patternBookEditionValidation.matcher(form.getBookName());
        if(!matcher.matches()) {
            validationData.addErrorCode("error.book.edition");
        }
        matcher = patternReliaseDateValidation.matcher(form.getReliaseDate());
        if(!matcher.matches()) {
            validationData.addErrorCode("error.wrong.date");
        }
        matcher = patternQuantityValidation.matcher(form.getCount());
        if(!matcher.matches()) {
            validationData.addErrorCode("error.wrong.quantity");
        }

            if (validationData.getErrorCodes().isEmpty()) {
            validationData.setValidationResult(true);
        }
        return validationData;
    }
}
