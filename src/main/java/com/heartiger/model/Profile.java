package com.heartiger.model;

public class Profile {
    private String name;
    private String gender;
    private String age;
    private String height;
    private String weight;
    private String income;
    private String marriage;
    private String education;
    private String occupation;
    private String residence;
    private String constellation;
    private String workPlace;
    private Boolean house;
    private Boolean car;
    private Boolean smoking;
    private Boolean drinking;
    private Boolean children;

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public void setHouse(Boolean house) {
        this.house = house;
    }

    public void setCar(Boolean car) {
        this.car = car;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public void setDrinking(Boolean drinking) {
        this.drinking = drinking;
    }

    public void setChildren(Boolean children) {
        this.children = children;
    }

    private String wantKids;

    public void setWantKids(String wantKids) {
        this.wantKids = wantKids;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", income='" + income + '\'' +
                ", marriage='" + marriage + '\'' +
                ", education='" + education + '\'' +
                ", occupation='" + occupation + '\'' +
                ", residence='" + residence + '\'' +
                ", constellation='" + constellation + '\'' +
                ", workPlace='" + workPlace + '\'' +
                ", house=" + house +
                ", car=" + car +
                ", smoking=" + smoking +
                ", drinking=" + drinking +
                ", children=" + children +
                ", wantKids='" + wantKids + '\'' +
                '}';
    }
}
