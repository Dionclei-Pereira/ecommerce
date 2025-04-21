package me.dionclei.ecommerce_product_service.exceptions;

import java.time.Instant;

public record StandardError(Instant timestamp, Integer status, String error, String message, String path) {

}
