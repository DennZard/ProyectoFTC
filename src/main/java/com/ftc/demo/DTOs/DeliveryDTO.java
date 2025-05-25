package com.ftc.demo.DTOs;

import java.sql.Date;

public record DeliveryDTO(long id, Date added,
		UserDTO customer, StatusDTO status,
		String destination, EmployeeDTO employee, ProductDTO product) {

}
