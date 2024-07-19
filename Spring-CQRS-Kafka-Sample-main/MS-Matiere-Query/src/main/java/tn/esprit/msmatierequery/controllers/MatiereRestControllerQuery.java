package tn.esprit.msmatierequery.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import tn.esprit.dto.MatiereDto;
import tn.esprit.msmatierequery.services.IMatiereService;

import java.util.List;

@RestController
@RequestMapping("/matieres")
@RequiredArgsConstructor
public class MatiereRestControllerQuery {

    private final IMatiereService matiereService;

    @GetMapping
    public List<MatiereDto> getMatieres(){
        return matiereService.getMatieres();
    }

    @GetMapping("{id}")
    public MatiereDto getMatiere(@PathVariable long id){
        return matiereService.getMatiere(id);
    }

    @GetMapping("name/{name}")
    public MatiereDto getMatiere(@PathVariable String name){
        return matiereService.getMatiereByName(name);
    }







}
