package com.company.retailapi.messages;

import java.time.LocalDate;
import java.util.Objects;

public class LevelUpMessage {
    private int levelupId;
    private int customerId;
    private int point;
    private LocalDate memberDate;

    public LevelUpMessage(){}
    public LevelUpMessage(int levelupId, int customerId, int point, LocalDate memberDate){
        this.levelupId = levelupId;
        this.customerId = customerId;
        this.point = point;
        this.memberDate = memberDate;
    }

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
        LevelUpMessage that = (LevelUpMessage) o;
        return levelupId == that.levelupId &&
                customerId == that.customerId &&
                point == that.point &&
                Objects.equals(memberDate, that.memberDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelupId, customerId, point, memberDate);
    }

    @Override
    public String toString() {
        return "LevelUpMessage{" +
                "levelupId=" + levelupId +
                ", customerId=" + customerId +
                ", point=" + point +
                ", memberDate=" + memberDate +
                '}';
    }
}
//    private int levelupId;
//    private int customerId;
//    private int point;
//    private LocalDate memberDate;
