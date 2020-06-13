package com.company.LevelUpService.viewModel;

import com.company.LevelUpService.models.LevelUp;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class LevelUpViewModel {

    private int levelupId;
    @NotNull(message = "Customer id is required")
    private int customerId;
    @NotNull(message = "number of points is required")
    private int point;
    @NotNull(message = "MemberDate is required")
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
        LevelUpViewModel that = (LevelUpViewModel) o;
        return getLevelupId() == that.getLevelupId() &&
                getCustomerId() == that.getCustomerId() &&
                getPoint() == that.getPoint() &&
                Objects.equals(getMemberDate(), that.getMemberDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLevelupId(), getCustomerId(), getPoint(), getMemberDate());
    }

    @Override
    public String toString() {
        return "LevelUpViewModel{" +
                "levelupId=" + levelupId +
                ", customerId=" + customerId +
                ", point=" + point +
                ", memberDate=" + memberDate +
                '}';
    }
}


