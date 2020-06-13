package com.company.LevelUpService.controller;
import com.company.LevelUpService.exceptions.NotFoundException;
import com.company.LevelUpService.models.LevelUp;
import com.company.LevelUpService.serviceLayer.LevelUpServiceLayer;
import com.company.LevelUpService.viewModel.LevelUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.awt.image.Kernel;
import java.security.Key;
import java.util.List;

@RestController
//@RefreshScope
//@CacheConfig(cacheNames = {"levelUp"})
@RequestMapping(value = "/levelUp")
public class LevelUpController {

    @Autowired
    LevelUpServiceLayer levelUpServiceLayer;


 //   @CachePut(key = "#result.getLevelUpId()")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public LevelUpViewModel addLevelup(@RequestBody @Valid LevelUpViewModel levelUpVm) {
        return levelUpServiceLayer.addLevelup(levelUpVm);
    }
 //   @Cacheable
    @GetMapping("/{id}")//Another way to set the Rest API Get mapping
    @ResponseStatus(HttpStatus.OK)
    public LevelUpViewModel getlevelupByid(@PathVariable("id") int levelupId) {
        LevelUpViewModel levelupVM = levelUpServiceLayer.findLevelupById(levelupId);
        if (levelupVM == null)
            throw new NotFoundException("levelUp could not be retrieved for id " + levelupId);
        return levelupVM;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUpViewModel> getAlllevelup(){
        return levelUpServiceLayer.getAllLevelups();
    }

//    @CacheEvict
    @DeleteMapping("/{id}")//Another way to set the Rest API Delete mapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable("id") int levelupId) {
        levelUpServiceLayer.deleteLevelup(levelupId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelup(@PathVariable("id") int leveupId, @RequestBody @Valid LevelUpViewModel levelUpViewModel ) {
        if (levelUpViewModel.getLevelupId()==0)
            levelUpViewModel.setLevelupId(leveupId);
        if(leveupId != levelUpViewModel.getLevelupId()) {
            throw new IllegalArgumentException("Levelup ID on path must match the ID in the Levelup object");

        }
        levelUpServiceLayer.updateLevelUp(levelUpViewModel);
    }
}
