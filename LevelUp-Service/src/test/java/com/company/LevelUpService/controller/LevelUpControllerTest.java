package com.company.LevelUpService.controller;

import com.company.LevelUpService.serviceLayer.LevelUpServiceLayer;
import com.company.LevelUpService.viewModel.LevelUpViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LevelUpController.class)
public class LevelUpControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LevelUpServiceLayer levelUpServiceLayer;

    LevelUpViewModel leveluptoCreate;
    LevelUpViewModel levelUpdb;
    List<LevelUpViewModel> levelUpList;



    //Mapper to turn objects from java to JSON
    private ObjectMapper mapper = new ObjectMapper();



    @Before
    public void setUp() throws Exception {
        //setting up test objects
        leveluptoCreate = new LevelUpViewModel();
        leveluptoCreate.setCustomerId(2);
        leveluptoCreate.setMemberDate(LocalDate.of(2019,12,12));
        leveluptoCreate.setPoint(3);levelUpdb = new LevelUpViewModel();

        levelUpdb.setLevelupId(1);
        levelUpdb.setCustomerId(2);
        levelUpdb.setMemberDate(LocalDate.of(2019,12,12));
        levelUpdb.setPoint(3);



        levelUpList = new ArrayList<>();
        levelUpList.add(levelUpdb);

        //fire UP
        setupServiceMock();



    }

    @Test
    public void shouldAddLevelup() throws Exception{
        //Assemble
        String inputJson = mapper.writeValueAsString(leveluptoCreate);
        String outputJson =mapper.writeValueAsString(levelUpdb);
        System.out.println(inputJson);
        System.out.println(outputJson);
        //Act
        mockMvc.perform(post("/levelUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON))
                //Assert
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetlevelupByid()  throws Exception{
        String outputJson = mapper.writeValueAsString(levelUpdb);

        //Act
        mockMvc.perform(get("/levelUp/1"))
                //Assert
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAlllevelup() throws Exception {
        String outputJson = mapper.writeValueAsString(levelUpList);
        //Act
        mockMvc.perform(get("/levelUp"))
                //Assert
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));


    }

    @Test
    public void deleteLevelUp()  throws Exception{
        //Act
        mockMvc.perform(delete("/levelUp/1"))
                //Assert
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateLevelup()  throws Exception{
        //Assemble
        String inputJson = mapper.writeValueAsString(levelUpdb);
        //Act
        mockMvc.perform(put("/levelUp/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON))
                //Assert
                .andExpect(status().isOk());
    }

    //helper method
    private void setupServiceMock(){
        doReturn(levelUpdb).when(levelUpServiceLayer).addLevelup(leveluptoCreate);
        doReturn(levelUpdb).when(levelUpServiceLayer).findLevelupById(1);
        doReturn(levelUpList).when(levelUpServiceLayer).getAllLevelups();
    }
}