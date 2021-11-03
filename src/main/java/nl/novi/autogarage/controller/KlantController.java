package nl.novi.autogarage.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;
import java.util.List;

@RestController
public class KlantController {

    //attributes
    private List<String> klanten = new ArrayList<>();

    //constructor
    public KlantController() {
        klanten.add("Iede");
    }

    @GetMapping(value =  "/klanten")
    public List<String> getKlanten() {
        return klanten;
    }
}
