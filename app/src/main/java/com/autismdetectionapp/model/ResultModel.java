package com.autismdetectionapp.model;

public class ResultModel {

    String key, childName, parentKey, score, dateTime;

    public ResultModel() {
    }

    public ResultModel(String childName, String score, String dateTime) {
        this.childName = childName;
        this.score = score;
        this.dateTime = dateTime;
    }

    public ResultModel(String childName, String parentKey, String score, String dateTime) {
        this.childName = childName;
        this.parentKey = parentKey;
        this.score = score;
        this.dateTime = dateTime;
    }

    public ResultModel(String key, String childName, String parentKey, String score, String dateTime) {
        this.key = key;
        this.childName = childName;
        this.parentKey = parentKey;
        this.score = score;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
