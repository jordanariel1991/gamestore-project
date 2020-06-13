package com.company.LevelUpService.dao;

import com.company.LevelUpService.models.LevelUp;

import java.util.List;

public interface LevelUpDao {

  LevelUp findLevelUpById(int id);

  List<LevelUp>getAllLevelUps();

  LevelUp saveLevelUp(LevelUp levelUp);

  void deleteLevelUpById(int id);

  void updateLevelup(LevelUp levelUp);
}
