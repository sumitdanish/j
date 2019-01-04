package com.example.jenkins;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class User {
    private Integer id;
    private String name;
    private String emailID;

}
