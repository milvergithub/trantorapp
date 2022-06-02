package com.trantor.trantorapp.service.mapper;

import com.trantor.trantorapp.domain.Message;
import com.trantor.trantorapp.service.dto.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MessageMapper
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
@Mapper(componentModel = "spring")
public interface MessageMapper {

    /**
     * MessageDto to Message
     *
     * @param message
     * @return MessageDto
     */
    MessageDto toDto(Message message);

    /**
     * Message to MessageDto
     *
     * @param messageDto
     * @return Message
     */
    @Mapping(target = "read", ignore = true)
    Message toEntity(MessageDto messageDto);
}
