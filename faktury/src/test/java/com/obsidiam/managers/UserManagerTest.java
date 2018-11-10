package com.obsidiam.managers;

import com.obsidiam.util.model.UserType;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertNotEquals;

public class UserManagerTest {
    @Test
    public void createUserTest(){
        UserManager userManager = new UserManager();
        int result = userManager.createUser(UserType.SELLER, "Name", "Surname", "Address");
        System.out.print(result);
        assertNotEquals(result, 0);
        assertNotEquals(result, -1);
    }
}
