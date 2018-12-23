package com.example.student.mymovieapp;

import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

public class ActorVO extends RealmObject {
    String Actor;

    public String getActor() {
        return Actor;
    }

    public void setActor(String actor) {
        Actor = actor;
    }
}
