package ru.arseniy.pp314.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public Long id;
    public String name;
    public String lastName;
    public Byte age;


}
