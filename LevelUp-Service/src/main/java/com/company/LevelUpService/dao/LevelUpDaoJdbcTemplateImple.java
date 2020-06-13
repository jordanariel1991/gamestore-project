package com.company.LevelUpService.dao;

import com.company.LevelUpService.models.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
@Repository
public class LevelUpDaoJdbcTemplateImple implements LevelUpDao {

    private static final String FIND_LEVELUP_BY_ID ="SELECT * from level_up where level_up_id = ?";
    private static final String FIND_ALL_LEVELUPS = "SELECT * from level_up";
    private  static final String SAVE_LEVELUP = "INSERT INTO level_up (customer_id,member_date,points) VALUES(?,?,?)";
    private static final String DELETE_LEVELUP = "DELETe FROM level_up WHERE level_up_id =?";
    private static final String UPDATE_LEVELUP ="UPDATE level_up SET customer_id =?, member_date =?,points =?";
    private static final String SELECT_LAST_INSERT_ID ="SELECT LAST_INSERT_ID()";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public LevelUp findLevelUpById(int id) {
        try {
            return jdbcTemplate.queryForObject(FIND_LEVELUP_BY_ID, this::mapper, id);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<LevelUp> getAllLevelUps() {
        return jdbcTemplate.query(FIND_ALL_LEVELUPS, this::mapper);
    }

    @Override
    public LevelUp saveLevelUp(LevelUp levelUp) {
        jdbcTemplate.update(SAVE_LEVELUP,
                levelUp.getCustomerId(),
                levelUp.getMemberDate(),
                levelUp.getPoint()
        );
        int id = jdbcTemplate.queryForObject(SELECT_LAST_INSERT_ID,Integer.class);
        levelUp.setLevelupId(id);
        return levelUp;
    }

    @Override
    public void deleteLevelUpById(int id) {
        jdbcTemplate.update(DELETE_LEVELUP,id);

    }


    @Override
    public void updateLevelup(LevelUp levelUp) {
        jdbcTemplate.update(UPDATE_LEVELUP,
                levelUp.getCustomerId(),
                levelUp.getMemberDate(),
                levelUp.getPoint());

    }

    // Helper Method
    private LevelUp mapper (ResultSet rs, int rowNum) throws SQLException {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(rs.getInt("customer_id"));
        levelUp.setMemberDate(rs.getDate("member_date").toLocalDate());
        levelUp.setPoint(rs.getInt("points"));
        levelUp.setLevelupId(rs.getInt("level_up_id"));

        return levelUp;
    }
}
