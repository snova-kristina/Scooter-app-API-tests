package ru.yandex.praktikum.step;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.client.CourierClient;
import ru.yandex.praktikum.dto.CreateCourierRequest;
import ru.yandex.praktikum.dto.LoginCourierRequest;

public class CourierSteps {

	private final CourierClient courierClient;
	public CourierSteps(CourierClient courierClient) {
		this.courierClient = courierClient;
	}
	@Step("Send POST request to /api/v1/courier for creation new courier")
	public ValidatableResponse createCourier(String login, String password, String firstName){
		CreateCourierRequest createCourierRequest = new CreateCourierRequest();
		createCourierRequest.setLogin(login);
		createCourierRequest.setPassword(password);
		createCourierRequest.setFirstName(firstName);
		return courierClient.createCourier(createCourierRequest).then();
	}

	@Step("Send POST request to /api/v1/courier/login for login")
	public ValidatableResponse login(String login, String password){
		LoginCourierRequest loginRequest = new LoginCourierRequest();
		loginRequest.setLogin(login);
		loginRequest.setPassword(password);
		return courierClient.login(loginRequest).then();
	}

	@Step("Send DELETE request to /api/v1/courier/login to delete courier")
	public ValidatableResponse deleteCourier(Integer id){
		return courierClient.deleteCourier(id)
				.then();
	}
}
