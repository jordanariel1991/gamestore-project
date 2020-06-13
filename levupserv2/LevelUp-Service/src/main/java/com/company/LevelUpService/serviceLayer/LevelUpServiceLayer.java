package com.company.LevelUpService.serviceLayer;

import com.company.LevelUpService.dao.LevelUpDao;
import com.company.LevelUpService.exceptions.NotFoundException;
import com.company.LevelUpService.models.LevelUp;
import com.company.LevelUpService.viewModel.LevelUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LevelUpServiceLayer {

    LevelUpDao levelUpDao;


    @Autowired
    public LevelUpServiceLayer(LevelUpDao levelUpDao) {
        this.levelUpDao = levelUpDao;
    }

    public LevelUpViewModel addLevelup(LevelUpViewModel levelUpViewModel) {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(levelUpViewModel.getCustomerId());
        levelUp.setMemberDate(levelUpViewModel.getMemberDate());
        levelUp.setPoint(levelUpViewModel.getPoint());

        levelUp = levelUpDao.saveLevelUp(levelUp);

        levelUpViewModel.setLevelupId(levelUp.getLevelupId());
        return levelUpViewModel;
    }

    public void updateLevelUp(LevelUpViewModel levelUpViewModel) {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(levelUpViewModel.getCustomerId());
        levelUp.setMemberDate(levelUpViewModel.getMemberDate());
        levelUp.setPoint(levelUpViewModel.getPoint());
        levelUpDao.updateLevelup(levelUp);
    }

    public LevelUpViewModel findLevelupById(int id) {
        LevelUp levelUp = levelUpDao.findLevelUpById(id);
        if (levelUp == null)
            throw new NotFoundException("not in db");
        else
            return buildLevelUp(levelUp);
    }

    public List<LevelUpViewModel> getAllLevelups() {
        return levelUpDao.getAllLevelUps().stream().map(this::buildLevelUp).collect(Collectors.toList());
    }

    public void deleteLevelup(int id) {
        levelUpDao.deleteLevelUpById(id);
    }


    private LevelUpViewModel buildLevelUp(LevelUp levelUp) {
        LevelUpViewModel viewModel = new LevelUpViewModel();
        viewModel.setLevelupId(levelUp.getLevelupId());
        viewModel.setCustomerId(levelUp.getCustomerId());
        viewModel.setMemberDate(levelUp.getMemberDate());
        viewModel.setPoint(levelUp.getPoint());

        return viewModel;


    }
}

