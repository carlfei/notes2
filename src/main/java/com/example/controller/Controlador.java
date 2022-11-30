package com.example.controller;

import com.example.model.Libros;
import com.example.repository.Biblioteca;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/home")
@Api(tags = "home")
@RequiredArgsConstructor
public class Controlador {
    @Autowired
    Biblioteca biblioteca;
    List<Libros> libros = new ArrayList<>();

    @PostMapping("/loger")
    @ApiOperation(value = "${Controlador.loger}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something  wrong"), //
            @ApiResponse(code = 422, message = "No valid username/password")})
    public String login(//
                        @ApiParam("Username") @RequestParam String username, //
                        @ApiParam("Password") @RequestParam String password) {
        return userService.signin(username, password);
    }


    @GetMapping("/list/{id}")
    public List<Libros> listar(@PathVariable(value = "id") Long id){

        return (biblioteca.findById(id)).stream().collect(Collectors.toList());

    }
    @GetMapping("/listAdd/{id}/{titulo}/{autor}")
    public List<Libros> anyadir(@PathVariable(value = "id") Long id,
                               @PathVariable(value = "titulo") String titulo,
                               @PathVariable(value = "autor") String autor){
                             libros.add(new Libros(id,titulo,autor));
                             biblioteca.saveAll(libros);


        return libros
                .stream()
                .collect(Collectors.toList());

    }



}
