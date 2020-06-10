package wingmusic.domain.music;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import wingmusic.domain.album.Album;
import wingmusic.domain.musicInfo.MusicInfo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicId;

    private String musicName;
    private String musicGenre;
    private String fileUri;
    private int trackNumber;
    private String lyrics;

    @Column
    @ColumnDefault("0")
    private Long likeCnt;

    @ManyToOne(targetEntity = Album.class, fetch = FetchType.EAGER)
    @JoinColumn(name="album_id")
    private Album toAlbum;


    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    Set<MusicInfo> infos = new HashSet<>();


    @Builder
    public Music(String musicName, String musicGenre, String fileUri,
                 int trackNumber, String lyrics, Album toAlbum) {

        this.musicName = musicName;
        this.musicGenre = musicGenre;
        this.fileUri = fileUri;
        this.trackNumber = trackNumber;
        this.lyrics = lyrics;
        this.toAlbum = toAlbum;
        this.likeCnt = 0L;
    }

    public void update(String musicName, String musicGenre, String fileUri,
                       int trackNumber, String lyrics) {

        this.musicName = musicName;
        this.musicGenre = musicGenre;
        this.fileUri = fileUri;
        this.trackNumber = trackNumber;
        this.lyrics = lyrics;
    }

    public void infosUpdate(Set<MusicInfo> infos) {
        this.infos = new HashSet<>(infos);
    }
}
