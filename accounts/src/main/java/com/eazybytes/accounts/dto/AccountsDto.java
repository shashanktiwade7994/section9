package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @Pattern(regexp = "$[0-9]{11}", message = "Account number should be 11 digits long")
    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
