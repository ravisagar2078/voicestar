package com.example.ravisagar.hci;

/**
 * Created by Ravi Sagar on 5/26/2017.
 */
public class Playlist {
    String recName;
    int likeCount;

    public Playlist(String recName,int likeCount)
    {
        this.recName = recName;
        this.likeCount = likeCount;
    }

    public String getRecName()
    {
     return   recName;

    }

    public  int getLikeCount()
    {
     return likeCount;
    }
}
