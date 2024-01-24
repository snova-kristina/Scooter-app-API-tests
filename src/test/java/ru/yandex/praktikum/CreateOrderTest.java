package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseTestOrder {
	private final String[] color;

	public CreateOrderTest(String[] color) {
		this.color = color;
	}

	@Parameterized.Parameters
	public static Object[][] data() {
		return new Object[][]{
				new String[][]{{"BLACK"}},
				new String[][]{{"GREY"}},
				new String[][]{{"BLACK", "GREY"}},
				new String[][]{{}},
		};
	}

	@Test
	@DisplayName("Check successful order creation")
	@Description("Successful order creation with different colors, both colors and without")
	public void createNewOrderReturnsOk() {
		String firstName = RandomStringUtils.randomAlphabetic(10);
		String lastName = RandomStringUtils.randomAlphabetic(10);
		String address = RandomStringUtils.randomAlphabetic(20);
		String metroStation = RandomStringUtils.randomAlphabetic(10);
		String phone = RandomStringUtils.randomNumeric(11);
		int rentTime = RandomUtils.nextInt(0, 365);
		String deliveryDate = "2030-01-01";
		String comment = RandomStringUtils.randomAlphabetic(20);

		orderSteps
				.createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color)
				.log().all()
				.statusCode(HttpStatus.SC_CREATED)
				.body("track", notNullValue());
	}
}
