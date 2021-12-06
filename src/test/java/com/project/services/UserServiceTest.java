package com.project.services;

import com.project.dao.impl.UserDaoImpl;
import com.project.entities.User;
import com.project.web.data.UserData;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDaoImpl userDao;
    @Mock
    private User user;
    @Mock
    private UserData userData;

    @Test
    void getUserByEmailTest() {
        userService.getUserByEmail("someEmail");
        verify(userDao).getByEmail("someEmail");
    }

    @Test
    void testIsUserExist() {
        when(userDao.getByEmail("someEmail")).thenReturn(user);
        boolean result = userService.isUserExist("someEmail");
        Assert.assertTrue(result);
    }

    @Test
    void testIsUserNotExist() {
        when(userDao.getByEmail("someEmail")).thenReturn(null);
        boolean result = userService.isUserExist("someEmail");
        Assert.assertFalse(result);
    }

    @Test
    void testCreateUser() {
        userService.createUser(user);
        verify(userDao).create(user);
    }

    @Test
    void testGetAllLibrarians() {
        userService.getAllLibrarians();
        verify(userDao).getUsersByRole("librarian");
    }

    @Test
    void testGetAllUsers() {
        userService.getAllUsers();
        verify(userDao).getUsersByRole("user");
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1);
        verify(userDao).deleteUser(1);
    }

    @Test
    void testBanUser() {
        when(userDao.getById(1)).thenReturn(user);
        userService.banUser(1,true);
        verify(user).setBanList(1);
        verify(userDao).update(user);

    }

    @Test
    void testUnBanUser() {
        when(userDao.getById(1)).thenReturn(user);
        userService.banUser(1,false);
        verify(user).setBanList(0);
        verify(userDao).update(user);

    }

    @Test
    void testGetUser() {
        when(userDao.getById(1)).thenReturn(user);
        when(user.getName()).thenReturn("someName");
        UserData result = userService.getUser(1);
        Assert.assertEquals("someName", result.getUserName());
    }
}