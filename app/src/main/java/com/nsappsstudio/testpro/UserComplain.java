package com.nsappsstudio.testpro;

public class UserComplain {
    public String name;
    public String address;
    public String complain;
    public String department;
    public String photoUrl;
    public double lat;
    public double lang;

    public UserComplain(String name, String address, String complain, String department, String photoUrl, double lat, double lang) {
        this.name = name;
        this.address = address;
        this.complain = complain;
        this.department = department;
        this.photoUrl = photoUrl;
        this.lat = lat;
        this.lang = lang;
    }

    public UserComplain() {
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getComplain() {
        return complain;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public double getLat() {
        return lat;
    }

    public double getLang() {
        return lang;
    }
}
