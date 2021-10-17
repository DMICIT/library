package com.project.services;


import com.project.forms.RegistrationForm;


public class ValidatorService {
    public static boolean validate (RegistrationForm form){
        boolean result = true;

        if (form.getEmail().length() < 7) {
            result = false;
        }
        if (!form.getPassword().equals(form.getConfirmPassword())){
            result = false;
        }
        return result;
    }
}
