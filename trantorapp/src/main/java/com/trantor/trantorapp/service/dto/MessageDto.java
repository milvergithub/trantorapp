package com.trantor.trantorapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * MessageDto
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private Long id;

    @NotBlank
    private String content;

    private Boolean read;

    private UserDto from;

    @Valid
    private UserDto to;
}
