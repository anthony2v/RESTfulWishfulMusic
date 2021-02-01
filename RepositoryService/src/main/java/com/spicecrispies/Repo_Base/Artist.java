package com.spicecrispies.Repo_Base;
public class Artist {

    private String nickname;
    private String firstname;
    private String lastname;
    private String bio;

    public Artist(){}

    public void setNickname(String nickname) { this.nickname = nickname;}

    public String getNickname() {
        return nickname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }


}
