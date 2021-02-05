import facts.NetflixPlans;
import models.users.Datum;
import models.users.RegisterUserInfo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.junit.Test;
import org.junit.runner.RunWith;
import questions.GetUsersQuestion;
import questions.ResponseCode;
import tasks.GetUsers;
import tasks.RegisterUsers;
import tasks.UpdateUsers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


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

        Datum user = new GetUsersQuestion().answeredBy(josimar)
                .getData().stream().filter(x -> x.getId() ==4).findFirst().orElse(null);

        josimar.should(
                seeThat("usuario no es nulo", act -> user, notNullValue())
        );

        josimar.should(
                seeThat("el email del usuario", act -> user.getEmail(), equalTo("eve.holt@reqres.in")),
                seeThat("el avatar del usuario", act -> user.getAvatar(), equalTo("avatar"))
        );
    }

    @Test
    public void registerUsers(){
        Actor josimar = Actor.named("Josimar the trainer")
                .whoCan(CallAnApi.at(restApiUrl));

        RegisterUserInfo registerUserInfo = new RegisterUserInfo();
        registerUserInfo.setName("morpheus");
        registerUserInfo.setJob("leader");
        registerUserInfo.setEmail("tracey.ramos@reqres.in");
        registerUserInfo.setPassword("serenity");

        josimar.attemptsTo(RegisterUsers.withInfo(registerUserInfo));


        josimar.should(
                seeThat("el codigo de respuesta", ResponseCode.was(),equalTo(200))
        );

    }

    @Test
    public void updateUsers(){
        Actor josimar = Actor.named("Josimar the trainer")
                .whoCan(CallAnApi.at(restApiUrl));

        RegisterUserInfo registerUserInfo = new RegisterUserInfo();
        registerUserInfo.setName("morpheus");
        registerUserInfo.setJob("zion resident");

        josimar.attemptsTo(UpdateUsers.withUpdate(registerUserInfo));


        josimar.should(
                seeThat("el codigo de respuesta", ResponseCode.was(),equalTo(200))
        );


    }

    @Test
    public void factTest(){
        Actor josimar = Actor.named("Josimar the trainer")
                .whoCan(CallAnApi.at(restApiUrl));
        josimar.has(NetflixPlans.toViewSeries());


    }

}
