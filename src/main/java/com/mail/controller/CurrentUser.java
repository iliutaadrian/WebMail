package com.mail.controller;

import com.mail.model.Users;

public abstract class CurrentUser {
    private Users currentUser = null;

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public Users getCurrentUser() {
        return currentUser;
    }
}
