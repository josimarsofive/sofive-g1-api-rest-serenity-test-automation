package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.users.RegisterUserInfo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.ResponseCode;
import tasks.RegisterUsers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterStepDefs {

    private static final String restApiUrl = "http://localhost:5000/api";
    Actor josimar;

    @Given("Josimar es un cliente que quiere poder administrar sus productos bancarios")
    public void josimar_es_un_cliente_que_quiere_poder_administrar_sus_productos_bancarios() {
        josimar = Actor.named("Josimar the trainer")
                .whoCan(CallAnApi.at(restApiUrl));
    }

    @When("el envia la información requerida para el registro")
    public void el_envia_la_información_requerida_para_el_registro() {
        RegisterUserInfo registerUserInfo = new RegisterUserInfo();
        registerUserInfo.setName("morpheus");
        registerUserInfo.setJob("leader");
        registerUserInfo.setEmail("tracey.ramos@reqres.in");
        registerUserInfo.setPassword("serenity");

        josimar.attemptsTo(RegisterUsers.withInfo(registerUserInfo));
    }

    @Then("el debe obtener una cuenta virtual para poder ingresar cuando lo requiera")
    public void el_debe_obtener_una_cuenta_virtual_para_poder_ingresar_cuando_lo_requiera() {
        josimar.should(
                seeThat("el codigo de respuesta", ResponseCode.was(),equalTo(200))
        );
    }
}
