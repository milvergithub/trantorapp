package com.trantor.trantorapp.service.impl;

import com.trantor.trantorapp.api.exception.ResourceNotFoundException;
import com.trantor.trantorapp.domain.Message;
import com.trantor.trantorapp.domain.User;
import com.trantor.trantorapp.repository.MessageRepository;
import com.trantor.trantorapp.repository.UserRepository;
import com.trantor.trantorapp.service.MessageService;
import com.trantor.trantorapp.service.dto.MessageDto;
import com.trantor.trantorapp.service.dto.MessageReadDto;
import com.trantor.trantorapp.service.mapper.MessageMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * MessageServiceImpl
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageRepository messageRepository,
                              UserRepository userRepository,
                              MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MessageDto> findAllMessages(Pageable pageable, String username) {
        Optional<User> fromOptional = userRepository.findByUsername(username);
        if (!fromOptional.isPresent()) {
            throw new ResourceNotFoundException("User not found for sending message :: " + username);
        }

        Page<Message> messages = messageRepository.findAllByFrom(fromOptional.get(), pageable);

        return messages.map(messageMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public MessageDto findMessageById(Long id) {
        Optional<Message> message = findMessage(id);

        return messageMapper.toDto(message.get());
    }

    @Override
    public MessageDto createMessage(MessageDto messageDto, String username) {
        Optional<User> toOptional = userRepository.findById(messageDto.getTo().getId());
        if (!toOptional.isPresent()) {
            throw new ResourceNotFoundException("User not found for receiving message :: " + messageDto.getTo().getId());
        }

        Optional<User> fromOptional = userRepository.findByUsername(username);
        if (!fromOptional.isPresent()) {
            throw new ResourceNotFoundException("User not found for sending message :: " + username);
        }

        Message message = messageMapper.toEntity(messageDto);
        message.setFrom(fromOptional.get());
        message.setTo(toOptional.get());
        message.setRead(Boolean.FALSE);
        message.setUpdatedDate(LocalDateTime.now());

        messageRepository.save(message);

        return messageMapper.toDto(message);
    }

    @Override
    public MessageDto markAsRedMessage(MessageReadDto messageReadDto) {
        Optional<Message> result = findMessage(messageReadDto.getId());

        Message message = result.get();
        message.setRead(true);
        message.setUpdatedDate(LocalDateTime.now());
        messageRepository.save(message);

        return messageMapper.toDto(message);
    }

    @Override
    public void deleteMessage(Long id) {
        Optional<Message> message = findMessage(id);

        messageRepository.delete(message.get());
    }

    private Optional<Message> findMessage(Long id) {
        Optional<Message> message = messageRepository.findById(id);

        if (!message.isPresent()) {
            throw new ResourceNotFoundException("Message not found for this id :: " + id);
        }
        return message;
    }
}
