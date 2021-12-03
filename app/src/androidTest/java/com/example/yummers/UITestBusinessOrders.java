package com.example.yummers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UITestBusinessOrders {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void login() {
        ViewInteraction materialButton = onView(withId(R.id.signIn));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(withId(R.id.email));
        appCompatEditText.perform(replaceText("jj@jjs.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(withId(R.id.password));
        appCompatEditText2.perform(replaceText("jj'sdiner"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(withId(R.id.signIn));
        materialButton2.perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void viewOrders() {
        ViewInteraction ordersButton = onView(withId(R.id.button2));
        ordersButton.perform(click());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        ViewInteraction pastOrdersButton = onView(withId(R.id.button3));
        pastOrdersButton.perform(click());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
