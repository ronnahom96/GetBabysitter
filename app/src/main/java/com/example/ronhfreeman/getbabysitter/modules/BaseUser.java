package com.example.ronhfreeman.getbabysitter.modules;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Ronh on 21/07/2016.
 */
public class BaseUser {

    String profileType;
    String name;
    Date birthDate;
    BigInteger fbid;
    String Bio;
    String[] pictureUrls;

    public BaseUser(String profileType, String name, Date birthDate, BigInteger fbid, String Bio, String[] pictureUrls) {
        this.profileType = profileType;
        this.name = name;
        this.birthDate = birthDate;
        this.fbid = fbid;
        this.Bio = Bio;
        this.pictureUrls = pictureUrls;
    }

    public BaseUser() {

    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public BigInteger getFbid() {
        return fbid;
    }

    public void setFbid(BigInteger fbid) {
        this.fbid = fbid;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String[] getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(String[] pictureUrls) {
        this.pictureUrls = pictureUrls;
    }
}
