package com.company.adminapi.util.feign;

import com.company.adminapi.exception.NotFoundException;
import com.company.adminapi.views.LevelUpView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "level-service")
public interface LvlClient {

    // I think I only need the one custom method here, but will add all crud for future proofing

    @RequestMapping(value = "/levelup/customer/{id}", method = RequestMethod.GET)
    public int getLevelUpPointsByCustomerId(int id);

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public LevelUpView addLevelUp(@RequestBody @Valid LevelUpView levelUpView);


    @GetMapping("/{id}")//Another way to set the Rest API Get mapping
    @ResponseStatus(HttpStatus.OK)
    public LevelUpView getLevelUpById(@PathVariable("id") int id);


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUpView> getAllLevelUp();



    @DeleteMapping("/{id}")//Another way to set the Rest API Delete mapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable("id") int id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@PathVariable("id") int id, @RequestBody @Valid LevelUpView levelUpView );

}
