package com.project.web.commands;

import com.project.entities.User;
import com.project.forms.LoginForm;
import com.project.services.PenaltyService;
import com.project.services.UserService;
import com.project.services.ValidatorService;
import com.project.web.data.ValidationData;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class LoginCommandTest {

    @InjectMocks
    private LoginCommand loginCommand;

    @Mock
    private UserService userService;

    @Mock
    private PenaltyService penaltyService;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private User user;
    @Mock
    private ValidationData validationData;


    @Test
    void executeGetTest() {
        String result = loginCommand.executeGet(request,response);
        Assert.assertEquals("login.jsp", result);
    }

    @Test
    void validationFalseTest() {
        when(request.getParameter("email")).thenReturn("testEmail");
        when(request.getParameter("password")).thenReturn("testPassword");
        when(userService.getUserByEmail("testEmail")).thenReturn(user);
        when(validatorService.validate(any(LoginForm.class))).thenReturn(validationData);
        when(validationData.isValidationResult()).thenReturn(false);
        when(validationData.getErrorCodes()).thenReturn(Collections.singletonList("errors"));

        String result = loginCommand.executePost(request,response);
        Assert.assertEquals("login.jsp",result);
        verify(request).setAttribute("errorMessages",Collections.singletonList("errors"));

    }

    @Test
    void userNullTest() {

        when(request.getParameter("email")).thenReturn("testEmail");
        when(request.getParameter("password")).thenReturn("testPassword");
        when(userService.getUserByEmail("testEmail")).thenReturn(null);
        when(validationData.isValidationResult()).thenReturn(true);
        when(validatorService.validate(any(LoginForm.class))).thenReturn(validationData);

        String result = loginCommand.executePost(request,response);
        Assert.assertEquals("redirect:registration",result);
        verify(request).setAttribute("errorMessages","User not found, please register");

    }

    @Test
    void notEqualsPasswordsTest() {

        when(request.getParameter("email")).thenReturn("testEmail");
        when(request.getParameter("password")).thenReturn("testPassword");
        when(userService.getUserByEmail("testEmail")).thenReturn(user);
        when(validatorService.validate(any(LoginForm.class))).thenReturn(validationData);
        when(validationData.getErrorCodes()).thenReturn(Collections.singletonList("errors"));

        String result = loginCommand.executePost(request,response);
        Assert.assertEquals("login.jsp",result);
        verify(request).setAttribute("errorMessages",Collections.singletonList("errors"));
    }

    @Test
    void userBanListTest() {
        when(request.getParameter("email")).thenReturn("testEmail");
        when(request.getParameter("password")).thenReturn("testPassword");
        when(userService.getUserByEmail("testEmail")).thenReturn(user);
        when(validationData.isValidationResult()).thenReturn(true);
        when(validatorService.validate(any(LoginForm.class))).thenReturn(validationData);
        when(user.getBanList()).thenReturn(1);
        when(user.getPassword()).thenReturn("testPassword");

        String result = loginCommand.executePost(request, response);
        Assert.assertEquals("ban-page.jsp", result);
    }
}