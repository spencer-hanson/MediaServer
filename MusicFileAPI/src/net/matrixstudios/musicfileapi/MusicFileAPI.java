package net.matrixstudios.musicfileapi;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Spencer Hanson on 1/12/16.
 */
public class MusicFileAPI {
//Wrapper for: https://bitbucket.org/ijabz/jaudiotagger/downloads
    private Tag tag;
    private AudioHeader header;
    private File file;

    public String getAlbum() {
        return tag.getFirst(FieldKey.ALBUM);
    }
    public String getArtist() {
        return tag.getFirst(FieldKey.ARTIST);
    }
    public String getTitle() {
        return tag.getFirst(FieldKey.TITLE);
    }
    public String getComment() {
        return tag.getFirst(FieldKey.COMMENT);
    }
    public String getYear() {
        return tag.getFirst(FieldKey.YEAR);
    }
    public String getTrack() {
        return tag.getFirst(FieldKey.TRACK);
    }
    public int getTrackLengthInSeconds() {
        return header.getTrackLength();
    }
    public String getSampleRate() {
        return header.getSampleRate();
    }
    public File getFile() { return file; }

    public static final String[] SUPPORTED_FILE_EXTENSIONS = {
        "mp3", "flac", "ogg", "mp4", "aiff", "wav", "wma", "m4a"
    };

    public static boolean isValidFile(File file) {
        if(file.isDirectory()) { return false; }
        boolean valid = false;
        String fileName = file.getName();
        for(String extension : SUPPORTED_FILE_EXTENSIONS) {
            if(fileName.toLowerCase().endsWith(extension)) {
                valid = true;
            }
        }
        return valid;
    }

    public MusicFileAPI(File file) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        this.file = file;
        AudioFile aFile = AudioFileIO.read(file);
        tag = aFile.getTag();
        header = aFile.getAudioHeader();
    }

    public String[] getAllData() {
        String[] data = {
                "Artist: \'"+tag.getFirst(FieldKey.ARTIST)+"\'",
                "Album: \'"+tag.getFirst(FieldKey.ALBUM)+"\'",
                "Title: \'"+tag.getFirst(FieldKey.TITLE)+"\'",
                "Comment: \'"+tag.getFirst(FieldKey.COMMENT)+"\'",
                "Year: \'"+tag.getFirst(FieldKey.YEAR)+"\'",
                "Track: \'"+tag.getFirst(FieldKey.TRACK)+"\'",
                "Disc-No. \'"+tag.getFirst(FieldKey.DISC_NO)+"\'",
                "Composer \'"+tag.getFirst(FieldKey.COMPOSER)+"\'",
                "Artist_Sort: \'"+tag.getFirst(FieldKey.ARTIST_SORT)+"\'",
                "Track Length: \'"+header.getTrackLength() + "\'",
                "Sample Rate: \'"+header.getSampleRate()+"\'"
        };

        return data;
    }



}