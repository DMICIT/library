package com.project.web.commands;

import com.project.forms.RegistrationForm;
import com.project.services.UserService;
import com.project.services.ValidatorService;
import com.project.web.data.ValidationData;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationCommandTest {

    @InjectMocks
    private RegistrationCommand registrationCommand;
    @Mock
    private ValidationData validationData;
    @Mock
    private UserService userService;
    @Mock
    private ValidatorService validatorService;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;
    @Mock
    private RegistrationForm form;

    @Test
    void returnRegistrationPageTest() {
        String result = registrationCommand.executeGet(request, response);

        Assert.assertEquals("registration.jsp", result);
    }

    @Test
    void testValidationFalse() {

        when(validatorService.validate(any(RegistrationForm.class))).thenReturn(validationData);
        when(validationData.isValidationResult()).thenReturn(false);
        when(validationData.getErrorCodes()).thenReturn(Collections.singletonList("errors"));

        String result = registrationCommand.executePost(request, response);
        verify(request).setAttribute("errorMessages", Collections.singletonList("errors"));

        Assert.assertEquals("registration.jsp", result);
    }

    @Test
    void testValidationTrue() {
        when(request.getParameter("name")).thenReturn("someEmail");
        when(request.getParameter("email")).thenReturn("someEmail");
        when(validatorService.validate(any(RegistrationForm.class))).thenReturn(validationData);
        when(validationData.isValidationResult()).thenReturn(true);
        when(userService.isUserExist("someEmail")).thenReturn(false);

        String result = registrationCommand.executePost(request, response);
        verify(userService).createUser(any(RegistrationForm.class));
        Assert.assertEquals("redirect:login", result);
    }

    @Test
    void testUserExist() {
        when(request.getParameter("name")).thenReturn("someEmail");
        when(request.getParameter("email")).thenReturn("someEmail");
        when(validatorService.validate(any(RegistrationForm.class))).thenReturn(validationData);
        when(validationData.isValidationResult()).thenReturn(true);
        when(validationData.getErrorCodes()).thenReturn(Collections.singletonList("errors"));

        when(userService.isUserExist("someEmail")).thenReturn(true);
        String result = registrationCommand.executePost(request, response);

        verify(validationData).addErrorCode("error.user.exist");

    }
}