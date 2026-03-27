package com.pao.laboratory04.playlist;

public record Song(String title, String artist, int durationSeconds)
        implements Comparable<Song> {

    @Override
    public int compareTo(Song other) {
        return this.title.compareTo(other.title);
    }
}