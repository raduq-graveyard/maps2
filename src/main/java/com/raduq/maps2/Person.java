package com.raduq.maps2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Raduq on 04/04/2016.
 */
public class Person {

    private String name;
    private List<Activity> activities = new ArrayList<>();

    public Person() {
        this.activities = new ArrayList<>();
    }

    public Person(String name){
        this();
        this.name = name;
    }

    public Person(List<Activity> activities) {
        this();
        this.activities = activities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(activities, person.activities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activities);
    }

    public Person addActivity(Activity activity) {
        this.activities.add(activity);
        return this;
    }
}
