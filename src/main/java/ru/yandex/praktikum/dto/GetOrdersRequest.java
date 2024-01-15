package ru.yandex.praktikum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrdersRequest {
	private int courierId;
	private String nearestStation;
	private int limit;
	private int page;
}
