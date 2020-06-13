package com.company.LevelUpService.dao;

import com.company.LevelUpService.models.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LevelUpDaoJdbcTemplateImpleTest {

    @Autowired
    LevelUpDao levelUpDao;

    @Before
    public void setUp() throws Exception {
        levelUpDao.getAllLevelUps().forEach(levelUp -> levelUpDao.deleteLevelUpById(levelUp.getLevelupId()));
    }

    @Test
    public void shouldAddGetDeleteLevelup() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setMemberDate(LocalDate.of(2012,12,12));
        levelUp.setPoint(2);

        levelUp =levelUpDao.saveLevelUp(levelUp);
        assertEquals(levelUp,levelUpDao.findLevelUpById(levelUp.getLevelupId()));

        levelUpDao.deleteLevelUpById(levelUp.getLevelupId());
        assertNull(levelUpDao.findLevelUpById(levelUp.getLevelupId()));
    }

    @Test
    public void getAllLevelUps() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setMemberDate(LocalDate.of(2012,12,12));
        levelUp.setPoint(2);

        levelUp =levelUpDao.saveLevelUp(levelUp);
        assertEquals(1,levelUpDao.getAllLevelUps().size());

    }


    @Test
    public void shouldUpdateLevelupById() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setMemberDate(LocalDate.of(2012,12,12));
        levelUp.setPoint(2);

        levelUp = levelUpDao.saveLevelUp(levelUp);

        levelUp.setPoint(3);
        levelUpDao.updateLevelup(levelUp);
        assertEquals(levelUp, levelUpDao.findLevelUpById(levelUp.getLevelupId()));
    }
}