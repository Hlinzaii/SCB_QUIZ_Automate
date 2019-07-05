package com.sourcey.materiallogindemo;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;

@RunWith(AndroidJUnit4.class)
public class TS_login {

    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void CreateAcc(){
        onView(withId(R.id.link_signup)).perform(click());
        onView(withId(R.id.input_name)).perform(ViewActions.scrollTo(),typeText("Hlin Jurairat"));
        onView(withId(R.id.input_address)).perform(ViewActions.scrollTo(), typeText("745/2 Soi Sudpraser6"));
        onView(withId(R.id.input_email)).perform(ViewActions.scrollTo(), typeText("khunhlin@gmail.com"));
        onView(withId(R.id.input_mobile)).perform(ViewActions.scrollTo(), typeText("0889246351"));
        onView(withId(R.id.input_password)).perform(ViewActions.scrollTo(), typeText("Hlin152531"));
        onView(withId(R.id.input_reEnterPassword)).perform(ViewActions.scrollTo(), typeText("Hlin2531"), closeSoftKeyboard());
        onView(withId(R.id.btn_signup)).perform(ViewActions.scrollTo(), click());
    }

    @Test
    public void loginFailWithPasswordless4Char() {

        // กรอกอีเมล์ และ พาสเวิด น้อยกว่า
        onView(withId(R.id.input_email)).perform(typeText("khunhlin@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText("hli"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        // Check the result.
        onView(withId(R.id.input_password)).check(matches(hasErrorText("between 4 and 10 alphanumeric characters")));
    }

    @Test
    public void loginFailWithPasswordMore10Char() {

        // กรอกอีเมล์ และ พาสเวิด มากกว่า
        onView(withId(R.id.input_email)).perform(typeText("khunhlin@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText("hlin152531234"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        // Check the result.
        onView(withId(R.id.input_password)).check(matches(hasErrorText("between 4 and 10 alphanumeric characters")));
    }

    @Test
    public void loginFailWithInvalidEmail() {
        activityTestRule.launchActivity(new Intent());
        // กรอกอีเมล์ไม่ถูกต้อง
        onView(withId(R.id.input_email)).perform(typeText("test test"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        // Check the result.
        onView(withId(R.id.input_email)).check(matches(hasErrorText("enter a valid email address")));
    }

    @Test
    public void loginFailWithoutEmail() {
        // ไม่กรอกอีเมล
        onView(withId(R.id.btn_login)).perform(click());

        // Check the result.
        onView(withId(R.id.input_email)).check(matches(hasErrorText("enter a valid email address")));
    }

    @Test
    public void loginFailWithInvalidEmail1() {

        //กรอกอีเมล์ผิด
        onView(withId(R.id.input_email)).perform(typeText("test@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText("Hlin2531"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        // Check result
        onView(withId(R.id.input_password)).check(matches(hasErrorText("enter a valid email address or password")));
    }

    @Test
    public void loginFailWithInvalidPassword() {

        // กรอกพาสเวิดผิด
        onView(withId(R.id.input_email)).perform(typeText("khunhlin@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText("hlin1234"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        // Check result
        onView(withId(R.id.input_password)).check(matches(hasErrorText("enter a valid email address or password")));
    }

    @Test
    public void loginSuccess() throws InterruptedException {

        // Type valid email and password then press login button.
        onView(withId(R.id.input_email)).perform(typeText("khunhlin@gmail.com"));
        onView(withId(R.id.input_password)).perform(typeText("Hlin2531"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        // Check the result
        Thread.sleep(5000);
    }
}
