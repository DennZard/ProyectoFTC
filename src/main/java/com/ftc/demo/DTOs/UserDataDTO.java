package com.ftc.demo.DTOs;

import java.util.HashSet;

public record UserDataDTO(long id,  String email, String username, String phone, HashSet<RolesDTO> roles, CompanyDTO company) {
	
}
