package com.example.student.mymovieapp;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

public class MovieVO extends RealmObject {
    private int number;
    private String title;

    public RealmList<ActorVO> actorList = new RealmList<>();

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<ActorVO> getActorList() {
        return actorList;
    }

    public void setActorList(RealmList<ActorVO> actorList) {
        this.actorList = actorList;
    }
}
