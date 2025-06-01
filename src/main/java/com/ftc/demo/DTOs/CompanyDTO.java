package com.ftc.demo.DTOs;

public record CompanyDTO(long id, String name, UserDTO owner, boolean active) {

}
