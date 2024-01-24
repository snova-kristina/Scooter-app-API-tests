package ru.yandex.praktikum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
	private String firstName;
	private String lastName;
	private String address;
	private String metroStation;
	private String phone;
	private int rentTime;
	private String deliveryDate;
	private String comment;
	private String[] color;
}