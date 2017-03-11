package cn.edu.nju.TrainingSystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by baiguofeng on 2017/3/10.
 */
@Entity
@Table(name = "student")
public class Student {
    private int id;
    private String password;
    private String name;
    private String bankCard;
    private Double balance;
    private Integer level;
    private Double point;
    private Date lastChargeDate;
    private String state;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public Date getLastChargeDate() {
        return lastChargeDate;
    }

    public void setLastChargeDate(Date lastChargeDate) {
        this.lastChargeDate = lastChargeDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double discount() {
        double discount = 1;
        if (level == 5) {
            discount = 0.75;
        } else if (level == 4) {
            discount = 0.8;
        } else if (level == 3) {
            discount = 0.85;
        } else if (level == 2) {
            discount = 0.9;
        } else if (level == 1) {
            discount = 0.95;
        }
        return discount;
    }

    public boolean income(double income) {
        balance += income;
        point -= income * 0.1;
        if (point >= 1000) {
            level = 5;
        } else if (point >= 500) {
            level = 4;
        } else if (point >= 200) {
            level = 3;
        } else if (point >= 100) {
            level = 2;
        } else if (point >= 50) {
            level = 1;
        } else {
            level = 0;
        }
        return true;
    }

    public boolean outcome(double outcome) {
        balance -= outcome;
        point += outcome * 0.15;
        if (point >= 1000) {
            level = 5;
        } else if (point >= 500) {
            level = 4;
        } else if (point >= 200) {
            level = 3;
        } else if (point >= 100) {
            level = 2;
        } else if (point >= 50) {
            level = 1;
        } else {
            level = 0;
        }
        return true;
    }

}
