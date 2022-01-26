package com.elcom.rabbitmq.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    private String Id;
    private String name;
}
