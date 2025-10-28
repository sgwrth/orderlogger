package dev.sgwrth.orderlogger.dto;

import java.util.List;

public record PlaceOrderDto(
		Long customerId,
		List<Long> articleIds
) {}
