package com.answerdigital.colourstest.controller;

import com.answerdigital.colourstest.model.Colour;
import com.answerdigital.colourstest.repository.ColoursRepository;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Colours")
public class ColoursController {

    @Autowired
    private ColoursRepository coloursRepository;

    @GetMapping
    public ResponseEntity<List<Colour>> getColours() {
        return new ResponseEntity(coloursRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colour> getColourByID(@PathVariable("id") long id) {
        Colour findColour;
        List<Colour> colourList = coloursRepository.findAll();
        for (int index = 0; index < colourList.size(); index ++){
            findColour = colourList.get(index);
            if (findColour.getId() == id){
                return new ResponseEntity<> (findColour, HttpStatus.OK);
            }
        }
        return new ResponseEntity<> (HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public Colour createColour(@Valid @RequestBody Colour newColour){
        return coloursRepository.save(newColour);
    }
}
