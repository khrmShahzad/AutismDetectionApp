package com.autismdetectionapp.model;

import androidx.annotation.DrawableRes;

public class GoodBadData {

    @DrawableRes
    int imageOne;
    @DrawableRes
    int imageTwo;

    public GoodBadData() {
    }

    public int getImageOne() {
        return imageOne;
    }

    public void setImageOne(int imageOne) {
        this.imageOne = imageOne;
    }

    public int getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(int imageTwo) {
        this.imageTwo = imageTwo;
    }

    public GoodBadData(int imageOne, int imageTwo) {
        this.imageOne = imageOne;
        this.imageTwo = imageTwo;
    }
}
