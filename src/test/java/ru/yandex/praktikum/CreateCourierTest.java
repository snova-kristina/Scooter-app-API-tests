package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static ru.yandex.praktikum.config.Config.BASE_URI;

public class CreateCourierTest extends BaseTestCourier {

	@Test
	@DisplayName("Check successful courier creation")
	public void createNewCourierReturnsOk() {
		String login = RandomStringUtils.randomAlphanumeric(10);
		String password = RandomStringUtils.randomAlphanumeric(10);
		String firstName = RandomStringUtils.randomAlphanumeric(10);

		courierSteps
				.createCourier(login, password, firstName)
				.log().all()
				.statusCode(HttpStatus.SC_CREATED)
				.body("ok", is(true));

		courierId = courierSteps
				.login(login, password)
				.extract().path("id");
	}

	@Test
	@DisplayName("Check courier creation with existent login")
	@Description("Getting conflict error as courier with used login already exist")
	public void createExistentCourierReturnsError() {
		String errorMessage = "Этот логин уже используется. Попробуйте другой.";
		String login = RandomStringUtils.randomAlphanumeric(10);
		String password = RandomStringUtils.randomAlphanumeric(10);
		String firstName = RandomStringUtils.randomAlphanumeric(10);

		courierSteps
				.createCourier(login, password, firstName);
		courierSteps
				.createCourier(login, "anotherPassword", "anotherFirstName")
				.log().all()
				.statusCode(HttpStatus.SC_CONFLICT)
				.body("code", is(409)).and().body("message", is(errorMessage));
	}

	@Test
	@DisplayName("Check courier creation without a required param")
	@Description("Getting bad request error as required param is absent")
	public void createCourierWithoutRequiredParamReturnsError() {
		String errorMessage = "Недостаточно данных для создания учетной записи";
		String password = RandomStringUtils.randomAlphanumeric(10);
		String firstName = RandomStringUtils.randomAlphanumeric(10);

		given()
				.baseUri(BASE_URI)
				.contentType(ContentType.JSON)
				.body("{\"password\":\"" + password + "\",\"firstName\":\"" + firstName + "\"}")
				.when()
				.post("/courier")
				.then()
				.log().all()
				.statusCode(HttpStatus.SC_BAD_REQUEST)
				.body("code", is(400)).and().body("message", is(errorMessage));
	}
}
