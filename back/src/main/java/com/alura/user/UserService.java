package com.alura.user;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class UserService {

    public List<User> getUsers() {
        return List.of(new User(
                1L,
                "Felipe",
                "Munevar",
                LocalDate.of(2000, Month.JANUARY,23),
                "male",
                "felipemunevarn@gmail.com",
                "backend",
                "user"
        ));
    }
}
