package com.dmytro_barsuk.spring_learning.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Client {
    @Value("${client.id}")
    private String id;
    @Value("${client.name}")
    private String fullName;
    @Value("${client.greeting}")
    private String greeting;
}
