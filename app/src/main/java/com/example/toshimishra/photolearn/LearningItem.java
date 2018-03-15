package com.example.toshimishra.photolearn;

/**
 * Created by toshimishra on 06/03/18.
 */

public class LearningItem extends Item {

    String userID;
    String photoDesc;
    String gps;

    public LearningItem() {
        super();
    }

    public LearningItem(String itemID,String titleID, String photoURL, String photoDesc, String GPS, String userID){
        super(titleID,photoURL);
        this.photoDesc = photoDesc;
        this.gps = GPS;
        this.userID = userID;
        this.itemID = itemID;
    }
    public void updateLearingItem(String photoDesc,String photoURL){
        super.update(photoURL);
        this.photoDesc = photoDesc;
        //todo UPDATE DB
    }
    @Override
    public  void delete(){
        //TODO update DB
    }

    public String getUserID() {
        return userID;
    }

    public String getPhotoDesc() {
        return photoDesc;
    }

    public String getGps() {
        return gps;
    }
}
