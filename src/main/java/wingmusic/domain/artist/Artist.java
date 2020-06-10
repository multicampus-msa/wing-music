package wingmusic.domain.artist;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wingmusic.domain.musicInfo.MusicInfo;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;

    private String artistName;
    private String artistCompany;
    private String artistGenre;
    private Date debutDate;
    private String video;
    private String imageUri;
    private String description;
    private String realName;
    private String account;
    private String bank;


    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    Set<MusicInfo> infos = new HashSet<>();


    @Builder
    public Artist(String artistName, String artistCompany, String artistGenre,
                  Date debutDate, String video, String imageUri,
                  String description, String realName, String account, String bank) {

        this.artistName = artistName;
        this.artistCompany = artistCompany;
        this.artistGenre = artistGenre;
        this.debutDate = debutDate;
        this.video = video;
        this.imageUri = imageUri;
        this.description = description;
        this.realName = realName;
        this.account = account;
        this.bank = bank;
    }

    public void update(String artistName, String artistCompany, String artistGenre,
                       Date debutDate, String video, String imageUri,
                       String description, String realName, String account, String bank) {

        this.artistName = artistName;
        this.artistCompany = artistCompany;
        this.artistGenre = artistGenre;
        this.debutDate = debutDate;
        this.video = video;
        this.imageUri = imageUri;
        this.description = description;
        this.realName = realName;
        this.account = account;
        this.bank = bank;
    }


}
