package com.trantor.trantorapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * User
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @Column(name = "msg_uid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq")
    @SequenceGenerator(name = "message_seq", sequenceName = "message_seq", allocationSize = 1)
    private Long id;

    @Column(name = "msg_body")
    private String content;

    @Column(name = "msg_read", columnDefinition = "boolean default false")
    private Boolean read;

    @Column(name = "msg_updated_date")
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msg_usr_uid_from")
    private User from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msg_usr_uid_to")
    private User to;
}
