package com.elcom.rabbitmq.model;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class MessageStatus {
    private Message message;
    private String status;
    private String context;
}
