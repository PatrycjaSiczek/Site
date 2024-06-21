package pl.umcs.oop.music;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import pl.umcs.oop.database.DatabaseConnection;

import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {

    @BeforeAll //lub @ BeforeEach Lub @AfterEach, @AfterAll
    public static void setup(){
        DatabaseConnection.connect("songs.db");}

    @AfterAll
    public static void close(){
        DatabaseConnection.disconnect();
    }

    @Test
    public void testCorrectIndex() throws SQLException {
        DatabaseConnection.connect("songs.db");
        Optional<Song> song = Song.Persistence.read(3);//pobieramy
        //song.get(); - zwraca piosenke ale nic innego nie robi
        assertEquals("Stairway to Heaven", song.get().title());
    }

    @Test
    public void testInCorrectIndex() throws SQLException {
        DatabaseConnection.connect("songs.db");
        Optional<Song> song = Song.Persistence.read(100);//pobieramy
        //song.get(); - zwraca piosenke ale nic innego nie robi
        assertTrue(song.isEmpty());
    }

    private static Stream<Arguments> songs(){
        return Stream.of(
                Arguments.arguments(1,"The Beatels", "Hey Judie", 431),
                Arguments.arguments(6,"Pink Floyd","Wish You Were Here",334),
                Arguments.arguments(4,"Bob Dylan","Like a Rolling Stone",373)
        );
    }

    @ParameterizedTest
    @MethodSource("songs")
    public void streamTest(int index, String artist, String title, int length) throws SQLException {
        Optional<Song> song = Song.Persistence.read(100);
        assertTrue(title,song.get().title());
    }

    @ParameterizedTest
    @CsvFileSource(files = "songs.csv", numLinesToSkip = 1)
    public void csvstreamTest(int index, String artist, String title, int length ) throws SQLException {
        Optional<Song> song = Song.Persistence.read(100);
        assertTrue(title, song.get().title());
    }
}