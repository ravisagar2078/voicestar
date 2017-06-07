package com.example.ravisagar.hci;

import android.app.Application;

/**
 * Created by Ravi Sagar on 11/25/2016.
 */
public class GlobalClass extends Application {
    private String name;

    private String name2;

    public String getName() {

        return name;
    }

    public void setName(String aName) {

        name = aName;

    }


    public String getName2() {

        return name2;
    }

    public void setName2(String aName) {

        name2 = aName;

    }

}
