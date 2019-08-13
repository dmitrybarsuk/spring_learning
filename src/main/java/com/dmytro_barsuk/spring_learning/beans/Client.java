package com.dmytro_barsuk.spring_learning.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Client {
    private String id;
    private String fullName;
}
