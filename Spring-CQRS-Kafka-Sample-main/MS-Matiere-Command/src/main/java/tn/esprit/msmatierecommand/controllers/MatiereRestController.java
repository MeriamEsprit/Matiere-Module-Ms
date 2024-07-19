package tn.esprit.msmatierecommand.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.dto.MatiereDto;
import tn.esprit.msmatierecommand.services.IMatiereService;

import java.util.Map;

@RestController
@RequestMapping("/matieres")
@RequiredArgsConstructor
public class MatiereRestController {
    private final IMatiereService productService;

    @PostMapping
    public MatiereDto add(@RequestBody MatiereDto matiereDto) {
        return productService.add(matiereDto);
    }

    @PatchMapping("{id}")
    public MatiereDto patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable long id){
        return productService.update(id,fields);
    }

    @DeleteMapping("{id}")
    public boolean delete( @PathVariable long id){
        return productService.delete(id);
    }







}
