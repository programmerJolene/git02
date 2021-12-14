package com.yjxxt.crs.query;

import com.yjxxt.crs.base.BaseQuery;

public class UserQuery extends BaseQuery {
    private String userName;
    private String phone;

    public UserQuery() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                "} " + super.toString();
    }
}
