package com.example.toshimishra.photolearn;

/**
 * Created by toshimishra on 06/03/18.
 */

public class Item {
    String itemID;
    String titleID;
    String photoURL;
    public Item(){}
    public  Item(String titleID,String photoURL){
        this.titleID = titleID;
        this.photoURL = photoURL;
        //TODO upload image here to firebase Cloud
        //TODO update ID
    }
    public void update(String photoURL){
        //todo delete old photo from cloud and upload new
        this.photoURL = photoURL;
    }
    public void delete(){

        //Todo delte photo from cloud
    }

    public String getItemID() {
        return itemID;
    }
}
