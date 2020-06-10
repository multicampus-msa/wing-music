package wingmusic.domain.musicInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wingmusic.domain.album.Album;
import wingmusic.domain.artist.Artist;
import wingmusic.domain.music.Music;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class MusicInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infoId;

    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name="music_id")
    private Music music;

    @ManyToOne
    @JoinColumn(name="album_id")
    private Album album;

    @Builder
    public MusicInfo(Artist artist, Music music, Album album) {
        this.artist = artist;
        this.music = music;
        this.album = album;
    }
}
