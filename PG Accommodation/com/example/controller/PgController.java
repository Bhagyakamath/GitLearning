package com.example.controller;

import com.example.service.*;
import com.example.entity.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pg")
public class PgController {

    @Autowired
    private PgService pgService;

    @GetMapping("/{cityId}")
    public List<PgPlace> getPgByCity(@PathVariable Long cityId) {
        return pgService.getPgByCity(cityId);
    }

    @GetMapping("/search/{locality}")
    public List<PgPlace> getPgByLocality(@PathVariable String locality) {
        return pgService.getPgByLocality(locality);
    }

    @GetMapping("/details/{id}")
    public PgPlace getPgDetails(@PathVariable Long id) {
        return pgService.getPgDetails(id);
    }

    @GetMapping("/owner/{id}")
    public Owner getOwnerDetails(@PathVariable Long id) {
        return pgService.getOwnerDetails(id);
    }
}
