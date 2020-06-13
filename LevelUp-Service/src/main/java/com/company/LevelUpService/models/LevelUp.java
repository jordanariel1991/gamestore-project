package com.company.LevelUpService.models;

import java.time.LocalDate;
import java.util.Objects;

public class LevelUp {

    private int levelupId;
    private int customerId;
    private int point;
    private LocalDate memberDate;

    public int getLevelupId() {
        return levelupId;
    }

    public void setLevelupId(int levelupId) {
        this.levelupId = levelupId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public LocalDate getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(LocalDate memberDate) {
        this.memberDate = memberDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelUp levelUp = (LevelUp) o;
        return getLevelupId() == levelUp.getLevelupId() &&
                getCustomerId() == levelUp.getCustomerId() &&
                getPoint() == levelUp.getPoint() &&
                Objects.equals(getMemberDate(), levelUp.getMemberDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLevelupId(), getCustomerId(), getPoint(), getMemberDate());
    }

    @Override
    public String toString() {
        return "LevelUp{" +
                "levelupId=" + levelupId +
                ", customerId=" + customerId +
                ", point=" + point +
                ", memberDate=" + memberDate +
                '}';
    }
}
