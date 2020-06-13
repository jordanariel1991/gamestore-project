package com.company.retailapi.util.feign;

import com.company.retailapi.model.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//need to put a fallback here
@FeignClient(name = "level-up-service")
public interface LevelUpClient {
    @RequestMapping(value = "/levelup/customer/{id}", method = RequestMethod.GET)
    public LevelUp getLevelUpPointsByCustomerId(int id);
}
//gets should be view models
