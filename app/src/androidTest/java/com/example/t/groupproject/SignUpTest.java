package com.example.t.groupproject;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.t.groupproject.View.SignUp;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


public class SignUpTest {
    @Rule
    public ActivityTestRule<SignUp> mActivityRule = new ActivityTestRule(SignUp.class);
    //click on login

    @Test
    public void pwdOnly() throws InterruptedException {
        //pwd only
        onView(withId(R.id.passwordField)).perform(click());
        onView(withId(R.id.passwordField)).perform(typeText("123456"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerButton)).perform(click());
        //Thread.sleep(1000);
        onView(withText("Please enter email"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }
    @Test
    public void emailOnly()throws InterruptedException{
        //email only
        onView(withId(R.id.passwordField)).perform(clearText());
        onView(withId(R.id.emailField)).perform(clearText(), typeText("jianxiahonglewis@163.com"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerButton)).perform(click());
        Thread.sleep(300);
        onView(withText("Please enter password"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }

    @Test
    public void invalidEmail()throws InterruptedException{
        //invalid email format
        onView(withId(R.id.emailField)).perform(clearText(), typeText("jianxiahonglewis.com"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerButton)).perform(click());
        onView(withText("Please enter password"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }

    @Test
    public void invalidEmailNPwd()throws InterruptedException{
        //invalid email with pwd
        onView(withId(R.id.emailField)).perform(clearText(), typeText("jianxiahonglewis.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.passwordField)).perform(clearText(), typeText("111"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerButton)).perform(click());
        onView(withText("Wrong email address"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }

    @Test
    public void registeredEmailWrongPwd()throws InterruptedException{
        //registered email with wrong pwd
        onView(withId(R.id.emailField)).perform(clearText(), typeText("jianxiahonglewis@163.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.passwordField)).perform(clearText(), typeText("111s111"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerButton)).perform(click());
        onView(withText("Password should has 8 digits long, at least 1 uppcase and 1 lower case."))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }

    @Test
    public void registeredEmail()throws InterruptedException{
        //registered email with pwd
        onView(withId(R.id.emailField)).perform(clearText(), typeText("jianxiahonglewis@163.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.passwordField)).perform(clearText(), typeText("111111"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerButton)).perform(click());
        onView(withText("Password should has 8 digits long, at least 1 uppcase and 1 lower case."))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());

    }

    @Test
    public void correctInfo(){
        //?valid new email & pwd registration
        onView(withId(R.id.emailField)).perform(clearText(), typeText("jianxiahonglewis@163.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.passwordField)).perform(clearText(), typeText("111111"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.registerButton)).perform(click());
        mActivityRule.finishActivity();
    }
    @Test
    public void ClickLogin(){
        //?click on login
        mActivityRule.finishActivity();
    }
}