package wingmusic.web.dto.artist;

import lombok.Getter;
import wingmusic.domain.artist.Artist;
import wingmusic.domain.musicInfo.MusicInfo;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class ArtistResponseDto {

    private final Long artistId;
    private final String artistName;
    private final String artistCompany;
    private final String artistGenre;
    private final Date debutDate;
    private final String video;
    private final String imageUri;
    private final String description;
    private final String realName;
    private final String account;
    private final String bank;
    private final Set<Map<String, String>> albumList;
    private final Set<Map<String, String>> musicList;

    public ArtistResponseDto(Artist entity) {
        this.artistId = entity.getArtistId();
        this.artistName = entity.getArtistName();
        this.artistCompany = entity.getArtistCompany();
        this.artistGenre = entity.getArtistGenre();
        this.debutDate = entity.getDebutDate();
        this.video = entity.getVideo();
        this.imageUri = entity.getImageUri();
        this.description = entity.getDescription();
        this.realName = entity.getRealName();
        this.account = entity.getAccount();
        this.bank = entity.getBank();

        this.albumList = new HashSet<>();
        this.musicList = new HashSet<>();

        for(MusicInfo info : entity.getInfos()) {
            Map<String, String> musicObj = new HashMap<>();
            Map<String, String> albumObj = new HashMap<>();

            albumObj.put("albumId", info.getAlbum().getAlbumId().toString());
            albumObj.put("albumName", info.getAlbum().getAlbumName());
            albumObj.put("ImageUri", info.getAlbum().getImageUri());
            albumObj.put("date", info.getAlbum().getDate().toString());
            musicObj.put("musicId", info.getMusic().getMusicId().toString());
            musicObj.put("musicName", info.getMusic().getMusicName());
            albumList.add(albumObj);
            musicList.add(musicObj);
        }

    }
}
