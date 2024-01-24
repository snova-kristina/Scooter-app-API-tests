package ru.yandex.praktikum.step;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.client.OrderClient;
import ru.yandex.praktikum.dto.CreateOrderRequest;
import ru.yandex.praktikum.dto.GetOrdersRequest;

public class OrderSteps {
	private final OrderClient orderClient;

	public OrderSteps(OrderClient orderClient) {
		this.orderClient = orderClient;
	}

	@Step("Send POST request to /api/v1/orders for creation new order")
	public ValidatableResponse createOrder(String firstName, String lastName, String address, String metroStation, String phone, Integer rentTime, String deliveryDate, String comment, String[] color) {
		CreateOrderRequest createOrderRequest = new CreateOrderRequest();
		createOrderRequest.setFirstName(firstName);
		createOrderRequest.setLastName(lastName);
		createOrderRequest.setAddress(address);
		createOrderRequest.setMetroStation(metroStation);
		createOrderRequest.setPhone(phone);
		createOrderRequest.setRentTime(rentTime);
		createOrderRequest.setDeliveryDate(deliveryDate);
		createOrderRequest.setComment(comment);
		createOrderRequest.setColor(color);
		return orderClient.createOrder(createOrderRequest).then().log().all();
	}

	@Step("Send GET request to /api/v1/orders for getting a list of orders")
	public ValidatableResponse getOrders(int courierId, String nearestStation, int limit, int page) {
		GetOrdersRequest getOrdersRequest = new GetOrdersRequest();
		getOrdersRequest.setCourierId(courierId);
		getOrdersRequest.setNearestStation(nearestStation);
		getOrdersRequest.setLimit(limit);
		getOrdersRequest.setPage(page);
		return orderClient.getOrders(getOrdersRequest).then().log().all();
	}
}
