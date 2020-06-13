package com.company.retailapi.viewModel;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class LevelUpViewModel {

    private int levelupId;
    @NotNull(message = "Customer id is required")
    private CustomerView customer;
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

    public CustomerView getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerView customer) {
        this.customer = customer;
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
        return levelupId == that.levelupId &&
                point == that.point &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(memberDate, that.memberDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelupId, customer, point, memberDate);
    }

    @Override
    public String toString() {
        return "LevelUpViewModel{" +
                "levelupId=" + levelupId +
                ", customer=" + customer +
                ", point=" + point +
                ", memberDate=" + memberDate +
                '}';
    }
}


