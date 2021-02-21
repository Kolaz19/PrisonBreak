package com.mygdx.game;

import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;

public class MusicManager {
    private ArrayList<MusicEntry> tracks;
    private boolean firstPlay;
    private boolean secondPlay;
    private boolean thirdPlay;

    public MusicManager () {
        tracks = new ArrayList<>();
        firstPlay = true;
        secondPlay = true;
        thirdPlay = true;
    }

    public void playCorrectTrack (ArrayList<Door> doors) {
        if (firstPlay) {
            tracks.get(0).getTrack().setLooping(false);
            tracks.get(0).getTrack().play();
            firstPlay = false;

            tracks.get(0).getTrack().setOnCompletionListener(new Music.OnCompletionListener() {
                @Override
                public void onCompletion(Music music) {
                    tracks.get(1).getTrack().setLooping(true);
                    tracks.get(1).getTrack().play();
                }
            });

        }

        for (Door door : doors) {
            if (door.getGroup() == 7 && door.isOpen() && secondPlay) {
                secondPlay = false;
                tracks.get(0).getTrack().stop();
                tracks.get(1).getTrack().stop();
                tracks.get(2).getTrack().setLooping(false);
                tracks.get(2).getTrack().play();
                tracks.get(2).getTrack().setOnCompletionListener(new Music.OnCompletionListener() {
                    @Override
                    public void onCompletion(Music music) {
                        tracks.get(3).getTrack().setLooping(true);
                        tracks.get(3).getTrack().play();
                    }
                });
            }

            if (door.getGroup() == 5 && door.isOpen() && thirdPlay) {
                thirdPlay = false;
                tracks.get(0).getTrack().stop();
                tracks.get(1).getTrack().stop();
                tracks.get(2).getTrack().stop();
                tracks.get(3).getTrack().stop();
                tracks.get(4).getTrack().setLooping(false);
                tracks.get(4).getTrack().play();
                tracks.get(4).getTrack().setOnCompletionListener(new Music.OnCompletionListener() {
                    @Override
                    public void onCompletion(Music music) {
                        tracks.get(5).getTrack().setLooping(true);
                        tracks.get(5).getTrack().play();
                    }
                });
            }

        }

    }

    public void addTrack(Music track, int number) {
        tracks.add(new MusicEntry(track, number));
    }

    public void dispose() {
        for (MusicEntry track : tracks) {
            track.getTrack().dispose();
        }
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
