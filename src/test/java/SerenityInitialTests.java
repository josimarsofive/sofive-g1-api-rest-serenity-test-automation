import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.junit.Test;
import org.junit.runner.RunWith;
import questions.ResponseCode;
import tasks.GetUsers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;


@RunWith(SerenityRunner.class)
public class SerenityInitialTests {

    private static final String restApiUrl = "http://localhost:5000/api";
    @Test
    public void getUsers(){
        Actor josimar = Actor.named("Josimar the trainer")
                .whoCan(CallAnApi.at(restApiUrl));

        josimar.attemptsTo(GetUsers.fromPage(2));

        //assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);

        josimar.should(
                seeThat("el codigo de respuesta", ResponseCode.was(),equalTo(200))
        );

    }

}
