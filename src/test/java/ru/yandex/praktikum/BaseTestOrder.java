package ru.yandex.praktikum;

import org.junit.Before;
import ru.yandex.praktikum.client.OrderClient;
import ru.yandex.praktikum.step.OrderSteps;

public abstract class BaseTestOrder {
	protected OrderSteps orderSteps;

	@Before
	public void setUp() {
		OrderClient orderClient = new OrderClient();
		orderSteps = new OrderSteps(orderClient);
	}
}
