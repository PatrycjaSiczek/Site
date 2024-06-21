package pl.umcs.oop.music;


import java.util.ArrayList;

public class PlayList extends ArrayList<Song> {

    public Song atSecond(int seconds) {
        if(seconds < 0){
            throw new IndexOutOfBoundsException("negative number");
        }

        for(Song song : this){
            seconds = seconds - song.duration();
            if(seconds <= 0){
                return song;
            }
        }
        throw new IndexOutOfBoundsException();
    }
}
 //   throw new IndexOutOfBoundsException();// jak nie znalazlisny takiej piosenki
//}