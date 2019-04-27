package com.example.service.bean;

import com.example.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserService {
    private User user;
    private List<User> users;
    private Map<String, User> userMap;
    private Set<User> userSet;

    public UserService() {
    }

    public UserService(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public void showUser() {
        System.out.println(user);
    }
}
