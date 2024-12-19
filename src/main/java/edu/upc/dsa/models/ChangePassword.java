package edu.upc.dsa.models;

public class ChangePassword {
    public String actualPassword;
    public String newPassword;

    public ChangePassword() {
    }

    public ChangePassword(String actualPassword, String newPassword) {
        this.actualPassword = actualPassword;
        this.newPassword = newPassword;
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
