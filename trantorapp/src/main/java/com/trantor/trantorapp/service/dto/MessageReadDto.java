package com.trantor.trantorapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * MessageReadDto
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageReadDto {

    @NotNull
    private Long id;
}
