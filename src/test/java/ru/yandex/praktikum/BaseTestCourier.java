package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import ru.yandex.praktikum.client.CourierClient;
import ru.yandex.praktikum.step.CourierSteps;

public abstract class BaseTestCourier {

	protected CourierSteps courierSteps;
	protected Integer courierId;

	@Before
	public void setUp() {
		CourierClient courierClient = new CourierClient();
		courierSteps = new CourierSteps(courierClient);
	}

	@After
	public void tearDown() {
		if (courierId != null){
			courierSteps.deleteCourier(courierId);
		}
	}
}
