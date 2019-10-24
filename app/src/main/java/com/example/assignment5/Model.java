package com.example.assignment5;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Model {


    public static class Lyric {
        public String word;
        public Lyric(String word) {
            this.word = word;
        }

        @NonNull
        @Override
        public String toString() {  // Overriding toString to properly return the word when called
            return word;
        }
    }

    public static Model model = null;

    public static Model getModel() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ArrayList<Lyric> lyricList;

    private Model(){
        lyricList = new ArrayList<Lyric>();
        loadModel();

    }

    private void loadModel(){
        lyricList.add(new Lyric("You"));
        lyricList.add(new Lyric("I knew"));
        lyricList.add(new Lyric("The best"));
        lyricList.add(new Lyric("Yes!"));
        lyricList.add(new Lyric("Tequila!"));
    }

}
