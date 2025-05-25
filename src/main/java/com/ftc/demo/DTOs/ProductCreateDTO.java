package com.ftc.demo.DTOs;


public record ProductCreateDTO(String name, long categoryId,
		float price ,long companyId, String image, int stock) {

}
