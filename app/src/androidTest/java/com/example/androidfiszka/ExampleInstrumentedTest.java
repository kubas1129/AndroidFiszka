package com.example.androidfiszka;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<CreateCategoryActivity> mCreateCategoryActivity = new ActivityTestRule<CreateCategoryActivity>(CreateCategoryActivity.class);


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.androidfiszka", appContext.getPackageName());
    }

    @Test
    public void testCreateCategory(){

        onView(withId(R.id.categoryName)).perform(typeText("Jezyk angielski"));

        onView(withId(R.id.categoryDesciption)).perform(typeText("zestaw fiszek do nauki jezyka angielskiego"));

    }

}
