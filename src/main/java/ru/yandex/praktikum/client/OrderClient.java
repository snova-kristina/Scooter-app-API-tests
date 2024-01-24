package ru.yandex.praktikum.client;

import io.restassured.response.Response;
import ru.yandex.praktikum.dto.CreateOrderRequest;
import ru.yandex.praktikum.dto.GetOrdersRequest;

public class OrderClient extends RestClient {

	public Response createOrder(CreateOrderRequest createOrderRequest) {
		return getRequestSpecification()
				.body(createOrderRequest)
				.when()
				.post("/orders");
	}
	public Response getOrders(GetOrdersRequest getOrdersRequest) {
		return getRequestSpecification()
				.body(getOrdersRequest)
				.when()
				.get("/orders");
	}
}
