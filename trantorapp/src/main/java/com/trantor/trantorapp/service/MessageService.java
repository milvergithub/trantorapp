package com.trantor.trantorapp.service;

import com.trantor.trantorapp.service.dto.MessageDto;
import com.trantor.trantorapp.service.dto.MessageReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * MessageService
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
public interface MessageService {
    Page<MessageDto> findAllMessages(Pageable pageable, String username);
    MessageDto findMessageById(Long id);
    MessageDto createMessage(MessageDto messageDto, String username);
    MessageDto markAsRedMessage(MessageReadDto messageReadDto);
    void deleteMessage(Long id);
}
