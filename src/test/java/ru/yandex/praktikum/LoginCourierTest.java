package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static ru.yandex.praktikum.config.Config.BASE_URI;

public class LoginCourierTest extends BaseTestCourier {

	@Test
	@DisplayName("Check successful courier login")
	@Description("Successful courier login with existent courier data")
	public void loginExistentCourierReturnsOk() {
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
		String login = RandomStringUtils.randomAlphanumeric(10);
		String password = RandomStringUtils.randomAlphanumeric(10);
		String firstName = RandomStringUtils.randomAlphanumeric(10);

		courierSteps
				.createCourier(login, password, firstName)
				.log().all();

		courierId = courierSteps
				.login(login, password)
				.extract().path("id");

		courierSteps
				.login(login, password)
				.log().all()
				.statusCode(HttpStatus.SC_OK)
				.body("id", notNullValue());
	}

	@Test
	@DisplayName("Check login with non existent courier data")
	@Description("Getting not found error as courier doesn't exist")
	public void loginNonExistentCourierReturnsError() {
		String errorMessage = "Учетная запись не найдена";
		String login = RandomStringUtils.randomAlphanumeric(10);
		String password = RandomStringUtils.randomAlphanumeric(10);
		String firstName = RandomStringUtils.randomAlphanumeric(10);

		courierSteps
				.createCourier(login, password, firstName);

		courierSteps
				.login("login", password)
				.log().all()
				.statusCode(HttpStatus.SC_NOT_FOUND)
				.body("code", is(404)).and().body("message", is(errorMessage));
	}

	@Test
	@DisplayName("Check login without a required param")
	@Description("Getting bad request error as required param is absent")
	public void loginWithoutRequiredParamReturnsError() {
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
		String errorMessage = "Недостаточно данных для входа";
		String login = RandomStringUtils.randomAlphanumeric(10);
		String password = RandomStringUtils.randomAlphanumeric(10);
		String firstName = RandomStringUtils.randomAlphanumeric(10);

		courierSteps
				.createCourier(login, password, firstName);

		given()
				.baseUri(BASE_URI)
				.contentType(ContentType.JSON)
				.body("{\"password\":\"" + password + "\"}")
				.when()
				.post("/courier/login")
				.then()
				.log().all()
				.statusCode(HttpStatus.SC_BAD_REQUEST)
				.body("code", is(400)).and().body("message", is(errorMessage));
	}
}
