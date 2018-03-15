package com.example.toshimishra.photolearn;

/**
 * Created by toshimishra on 12/03/18.
 */

public class State {
    private static LearningSession currentSession;
    private static LearningTitle currentLearningTitle;
    private static boolean editMode = false;
    private static boolean trainerMode = true;

    public static boolean isEditMode(){
        return editMode;
    }
    public static boolean isTrainerMode(){
        return trainerMode;
    }
    public static void setEditMode(boolean b){
        editMode = b;
    }
    public static void setTrainerMode(boolean b){
        trainerMode = b;
    }
    public static LearningSession getCurrentSession() {
        return currentSession;
    }

    public static void setCurrentSession(LearningSession currentSession) {
        State.currentSession = currentSession;
    }

    public static LearningTitle getCurrentLearningTitle() {
        return currentLearningTitle;
    }

    public static void setCurrentLearningTitle(LearningTitle currentLearningTitle) {
        State.currentLearningTitle = currentLearningTitle;
    }
}
