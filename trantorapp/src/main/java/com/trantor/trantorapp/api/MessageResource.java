package com.trantor.trantorapp.api;

import com.trantor.trantorapp.service.MessageService;
import com.trantor.trantorapp.service.dto.MessageDto;
import com.trantor.trantorapp.service.dto.MessageReadDto;
import com.trantor.trantorapp.util.HeaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/messages")
public class MessageResource {

    private final MessageService messageService;

    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public ResponseEntity<List<MessageDto>> findAllMessages(Pageable pageable, Authentication authentication) {
        Page<MessageDto> page = messageService.findAllMessages(pageable, authentication.getName());
        log.debug("REST request to findAllMessages : {}", page.getContent());
        HttpHeaders responseHeaders = new HttpHeaders();
        HeaderUtil.setHeaders(responseHeaders, page);
        return new ResponseEntity<>(page.getContent(), responseHeaders, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<MessageDto> createMessage(@Valid @RequestBody MessageDto messageDto, Authentication authentication) {
        log.debug("REST request to createMessage : {}", messageDto);
        MessageDto result = messageService.createMessage(messageDto, authentication.getName());
        log.debug("REST created to createMessage : {}", messageDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<MessageDto> updateMessage(@Valid @RequestBody MessageReadDto messageReadDto) {
        log.debug("REST request to updateMessage : {}", messageReadDto);
        MessageDto result = messageService.markAsRedMessage(messageReadDto);
        log.debug("REST updated to updateMessage : {}", messageReadDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") Long id) {
        log.debug("REST request to deleteMessage : {}", id);
        messageService.deleteMessage(id);
        log.debug("REST deleted to deleteMessage : {}", id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
