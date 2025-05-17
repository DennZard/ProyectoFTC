package com.ftc.demo.DTOs;

import java.sql.Date;

public record DeliveryDTO(long id, Date dateAdded,
		UserDTO user, StatusDTO status,
		String destination, EmployeeDTO employee, ProductDTO product) {

}
