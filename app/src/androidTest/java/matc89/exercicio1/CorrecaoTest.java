package matc89.exercicio1;

import android.content.pm.ActivityInfo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CorrecaoTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule = new ActivityScenarioRule<>(
            MainActivity.class);

    @Test
    public void textoInicial() {
        onView(withId(R.id.editNome))
                .check(matches(withText("")));
        onView(withId(R.id.labelMensagem))
                .check(matches(withText("Alô, Mundo!")));
    }

    @Test
    public void mudaTexto() {
        onView(withId(R.id.editNome))
                .perform(typeText("1234"));

        closeSoftKeyboard();
        onView(withId(R.id.btnCumprimentar))
                .perform(click());

        onView(withId(R.id.labelMensagem))
                .check(matches(withText("Alô, 1234!")));
    }

    @Test
    public void mantemLabelECaixaDeTextoAposRotacao() {
        onView(withId(R.id.editNome))
                .perform(typeText("1234"));

        closeSoftKeyboard();
        onView(withId(R.id.btnCumprimentar))
                .perform(click());

        onView(withId(R.id.editNome))
                .perform(typeText("56"));

        mActivityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<MainActivity>() {
            @Override
            public void perform(MainActivity activity) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });
//        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        onView(withId(R.id.labelMensagem))
                .check(matches(withText("Alô, 1234!")));
        onView(withId(R.id.editNome))
                .check(matches(withText("123456")));
    }
}
