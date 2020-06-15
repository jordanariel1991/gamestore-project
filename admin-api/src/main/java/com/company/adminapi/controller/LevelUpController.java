package com.company.adminapi.controller;

import com.company.adminapi.exception.NotFoundException;
import com.company.adminapi.service.ServiceLayer;
import com.company.adminapi.views.LevelUpView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/levelUp")
public class LevelUpController {

    @Autowired
    ServiceLayer serviceLayer;



    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public LevelUpView addLevelUp(@RequestBody @Valid LevelUpView levelUpView) {
        return serviceLayer.addLevelUp(levelUpView);
    }

    @GetMapping("/{id}")//Another way to set the Rest API Get mapping
    @ResponseStatus(HttpStatus.OK)
    public LevelUpView getlevelUpById(@PathVariable("id") int id) {
        LevelUpView levelupVM = serviceLayer.findLevelUpById(id);
        if (levelupVM == null)
            throw new NotFoundException("levelUp could not be retrieved for id " + id);
        return levelupVM;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUpView> getAllLevelUp(){
        return serviceLayer.getAllLevelUps();
    }


    @DeleteMapping("/{id}")//Another way to set the Rest API Delete mapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable("id") int id) {
        serviceLayer.deleteLevelUp(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@PathVariable("id") int id, @RequestBody @Valid LevelUpView levelUpView ) {
        if (levelUpView.getLevelupId()==0)
            levelUpView.setLevelupId(id);
        if(id != levelUpView.getLevelupId()) {
            throw new IllegalArgumentException("Levelup ID on path must match the ID in the Levelup object");

        }
        serviceLayer.updateLevelUp(levelUpView);
    }
}
