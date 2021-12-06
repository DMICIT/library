package com.project.web.commands;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)

class BanCommandTest {
    @InjectMocks
    private BanCommand banCommand;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Test
    void returnBanPage() {
        String result = banCommand.execute(request,response);
        Assert.assertEquals("ban-page.jsp",result);

    }
}