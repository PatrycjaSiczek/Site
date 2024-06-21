package pl.umcs.oop.music;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayListTest {

    @Test
    public void testIfNewPlayListIsEmpty(){
        PlayList playList = new PlayList();
        assertTrue(playList.isEmpty());
    }
@Test
    public void testIfNewPlayListHasOneSong(){
        Song song1 = new Song("artist","title",320);
        PlayList playList = new PlayList();
        playList.add(song1);
        assertEquals(playList.size(),1);
    }

    @Test
    public void ifHasSameSong(){
        Song song = new Song("artist","title", 10);
        PlayList playList = new PlayList();
        playList.add(song);
        assertTrue(song, playList.contains(song));
    }
    
    @Test
    void atSecond(){
        Song song1 = new Song("artist1", "title1", 50);
        Song song2 = new Song("artist1", "title1", 40);
        PlayList playList = new PlayList();
        playList.add(song1);
        playList.add(song2);
        int testtimestamp = 70;
        assertEquals(playList.atSecond(testtimestamp),song2);
    }

    @Test
    public void atSecondThrowxception() {
        Song song1 = new Song("artist1", "title1", 50);
        Song song2 = new Song("artist1", "title1", 40);
        PlayList playList = new PlayList();
        playList.add(song1);
        playList.add(song2);
        int testtimestamp = 100;
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> {
                    playList.atSecond(testtimestamp);
                }
        );
    }

    @Test
    public void atSecondThrowxceptionWhenNumberIsNegative() {
        Song song1 = new Song("artist1", "title1", 50);
        Song song2 = new Song("artist1", "title1", 40);
        PlayList playList = new PlayList();
        playList.add(song1);
        playList.add(song2);
        int testtimestamp = -20;
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> {
                    playList.atSecond(testtimestamp);}
                    ,"negative number"

        );
    }
}