package com.sherpasteven.sscte;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;

import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Registration;

/**
 * Created by salim_000 on 2015-11-12.
 */
public class RegistrationTest extends ApplicationTestCase<Application>{
    public RegistrationTest() {super(Application.class);}

    public void testCreateProfile(){
        Context testContext = null;
        Registration testRegistration = new Registration();
        testRegistration.setLocation("Wrestlemania");
        testRegistration.setUserEmail("johncena@gmail.com");
        testRegistration.setUserName("John Cena");

        assertFalse(testRegistration.isValidEmail());

        Profile testProfile = testRegistration.generateProfile(testContext);
        assertEquals(testProfile.getUser().getEmail(), testRegistration.getUserEmail());
        assertEquals(testProfile.getUser().getName(), testRegistration.getUserName());
        assertEquals(testProfile.getUser().getLocation(), testRegistration.getLocation());
    }

    public void testSaveLoadRegistration() {
        Context testContext = null;
        Registration testRegistration = new Registration();
        testRegistration.setLocation("Wrestlemania");
        testRegistration.setUserEmail("johncena@gmail.com");
        testRegistration.setUserName("John Cena");

        testRegistration.saveRegistration(testContext);

        Registration testRegistration2 = new Registration();
        testRegistration2.loadRegistration(testContext);
        assertEquals(testRegistration.getLocation(), testRegistration2.getLocation());
        assertEquals(testRegistration.getUserName(),testRegistration2.getUserName());
        assertEquals(testRegistration.getUserEmail(),testRegistration2.getUserEmail());
    }
}
