package com.mygdx.game;

import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;

public class MusicManager {
    private ArrayList<MusicEntry> tracks;

    public MusicManager () {
        tracks = new ArrayList<>();
    }

    public void playCorrectTrack () {

    }

    public void addTrack(Music track, int number) {
        tracks.add(new MusicEntry(track, number));
    }


    private class MusicEntry {
        private Music track;
        private int number;

        public MusicEntry(Music track, int number) {
            this.track = track;
            this.number = number;
        }

        public Music getTrack() {
            return track;
        }

        public int getNumber() {
            return number;
        }
    }
}
