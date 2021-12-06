package com.project.web.commands;

import com.project.entities.User;
import com.project.services.UserService;
import com.project.web.data.UserPrincipal;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PersonalAccountCommandTest {

    @InjectMocks
    private PersonalAccountCommand personalAccountCommand;
    @Mock
    private UserService userService;
    @Mock
    private User user;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private UserPrincipal userPrincipal;

    @Test
    void testExecute() {

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(userPrincipal);
        when(userPrincipal.getEmail()).thenReturn("someEmail");
        when(userService.getUserByEmail("someEmail")).thenReturn(user);

        String result = personalAccountCommand.execute(request, response);
        verify(request).setAttribute("user",user);
        Assert.assertEquals("personal-account.jsp",result);
    }
}