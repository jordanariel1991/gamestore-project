package com.company.LevelUpService.serviceLayer;

import com.company.LevelUpService.dao.LevelUpDao;
import com.company.LevelUpService.dao.LevelUpDaoJdbcTemplateImple;
import com.company.LevelUpService.models.LevelUp;
import com.company.LevelUpService.viewModel.LevelUpViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LevelUpServiceLayerTest {

    LevelUpServiceLayer levelUpServiceLayer;

    LevelUpDao levelUpDao;

    @Before
    public void setUp() throws Exception {
        setUpLevelupDaoMock();

        levelUpServiceLayer = new LevelUpServiceLayer(levelUpDao);
    }

    private void setUpLevelupDaoMock(){
        levelUpDao = mock(LevelUpDaoJdbcTemplateImple.class);

        LevelUp levelUpDB = new LevelUp();
        levelUpDB.setLevelupId(1);
        levelUpDB.setCustomerId(2);
        levelUpDB.setMemberDate(LocalDate.of(2019,12,12));
        levelUpDB.setPoint(3);

        LevelUp levelUptoCreate = new LevelUp();
        levelUptoCreate.setCustomerId(2);
        levelUptoCreate.setMemberDate(LocalDate.of(2019,12,12));
        levelUptoCreate.setPoint(3);

        List<LevelUp> levelUpList = new ArrayList<>();
        levelUpList.add(levelUpDB);

        doReturn(levelUpDB).when(levelUpDao).saveLevelUp(levelUptoCreate);
        doReturn(levelUpDB).when(levelUpDao).findLevelUpById(1);
        doReturn(levelUpList).when(levelUpDao).getAllLevelUps();


    }

    @Test
    public void shouldAddandGetLevelup() {
        LevelUpViewModel levelUp = new LevelUpViewModel();
        levelUp.setLevelupId(1);
        levelUp.setCustomerId(2);
        levelUp.setMemberDate(LocalDate.of(2019,12,12));
        levelUp.setPoint(3);

        levelUp =levelUpServiceLayer.addLevelup(levelUp);
        LevelUpViewModel fromService = levelUpServiceLayer.findLevelupById(levelUp.getLevelupId());
        assertEquals(levelUp, fromService);
    }


    @Test
    public void getAllLevelups() {
        LevelUpViewModel levelUp = new LevelUpViewModel();
        levelUp.setLevelupId(1);
        levelUp.setCustomerId(2);
        levelUp.setMemberDate(LocalDate.of(2019,12,12));
        levelUp.setPoint(3);

        levelUp =levelUpServiceLayer.addLevelup(levelUp);
        assertEquals(1,levelUpDao.getAllLevelUps().size());
    }


}