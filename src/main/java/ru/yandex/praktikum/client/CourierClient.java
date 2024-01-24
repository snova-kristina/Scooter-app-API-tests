package ru.yandex.praktikum.client;

import io.restassured.response.Response;
import ru.yandex.praktikum.dto.CreateCourierRequest;
import ru.yandex.praktikum.dto.LoginCourierRequest;

public class CourierClient extends RestClient {
	private static final String CREATE_PATH = "/courier";
	private static final String DELETE_PATH = "/courier/{id}";
	private static final String LOGIN_PATH = "/courier/login";
	public Response createCourier(CreateCourierRequest createCourierRequest) {
		return getRequestSpecification()
				.body(createCourierRequest)
				.when()
				.post(CREATE_PATH);
	}
	public Response login(LoginCourierRequest loginCourierRequest) {
		return getRequestSpecification()
				.body(loginCourierRequest)
				.when()
				.post(LOGIN_PATH);
	}

	public Response deleteCourier(Integer id) {
		return getRequestSpecification()
				.pathParam("id", id)
				.when()
				.delete(DELETE_PATH);
	}

}
