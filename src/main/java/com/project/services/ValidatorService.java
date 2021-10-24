package com.project.services;


import com.project.forms.LoginForm;
import com.project.forms.RegistrationForm;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidatorService {
    private static final Logger LOG = Logger.getLogger(ValidatorService.class);

    private static Pattern patternEmailValidation = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static boolean validate (RegistrationForm form){
        boolean result = true;

        if (form.getEmail().length() < 7) {
            result = false;
            LOG.info("Email less than 7 symbols");
        }
        if (!form.getPassword().equals(form.getConfirmPassword())){
            result = false;
            LOG.info("Invalid  password");
        }
        LOG.info("result is : " + result);
        return result;
    }
    public static boolean validate(LoginForm form) {
        boolean result = true;

        Matcher matcher = patternEmailValidation.matcher(form.getEmail());
        if (!matcher.matches()) {
            result = false;
        }
        return result;
    }
}
