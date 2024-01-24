package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderListTest extends BaseTestOrder{

	@Test
	@DisplayName("Check successful getting a list of orders")
	@Description("Get a list of orders")
	public void getOrderListReturnsOk(){
		RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
		int courierId = RandomUtils.nextInt();
		String nearestStation = RandomStringUtils.randomAlphabetic(8);
		int limit = RandomUtils.nextInt();
		int page = RandomUtils.nextInt();

		orderSteps.getOrders(courierId,nearestStation,limit,page)
				.statusCode(SC_OK)
				.body("orders", notNullValue());
	}
}
