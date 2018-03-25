package com.example.giovanni.xcodeiam.Model;

/**
 * Copied by vinicius on 16/03/2018.
 */

public class USER {

    private final String TABLE = "USER";
    private final String SQL_userId = "userId";
    private final String SQL_userCookie = "userCookie";
    private final String SQL_userName = "userName";
    private final String SQL_name = "name";
    private final String SQL_surname = "surname";
    private final String SQL_nameFull = "nameFull";
    private final String SQL_address = "address";
    private final String SQL_photo = "photo";

    private Integer userId;
    public static Integer userCookie;
    public static String userNameLocal;
    private String userName;
    private String name;
    private String surname;
    private String nameFull;
    private String address;
    private String photo;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }



}
