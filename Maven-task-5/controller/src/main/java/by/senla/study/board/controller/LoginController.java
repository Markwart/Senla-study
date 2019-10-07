package by.senla.study.board.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.dto.LoginDto;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@PostMapping
    public void login(@RequestBody LoginDto dto) {

    }
}
