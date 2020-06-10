package wingmusic.web.dto.album;

import lombok.Getter;
import wingmusic.domain.album.Album;
import wingmusic.domain.music.Music;
import wingmusic.domain.musicInfo.MusicInfo;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class AlbumResponseDto {

    private final Long albumId;
    private final String albumName;
    private final String albumGenre;
    private final String company;
    private final String distributor;
    private final Date date;
    private final String imageUri;
    private final String description;
    private final Set<Map<Object, Object>> musicList;
    private final Set<Map<String, String>> artistList;

    public AlbumResponseDto(Album entity) {
        this.albumId = entity.getAlbumId();
        this.albumName = entity.getAlbumName();
        this.albumGenre = entity.getAlbumGenre();
        this.company = entity.getCompany();
        this.distributor = entity.getDistributor();
        this.date = entity.getDate();
        this.imageUri = entity.getImageUri();
        this.description = entity.getDescription();

        this.musicList = new HashSet<Map<Object, Object>>();
        for(Music music : entity.getMusicList()) {
            Map<Object, Object> musicObj = new HashMap<>();
            musicObj.put("musicId", music.getMusicId().toString());
            musicObj.put("musicName", music.getMusicName());
            musicObj.put("fileUri", music.getFileUri());
            musicObj.put("trackNumber", music.getTrackNumber());

            Set<Object> artistList = new HashSet<>();

            for (MusicInfo info : music.getInfos()) {
                Map<Object, Object> artistObj = new HashMap<>();
                artistObj.put("artistId", info.getArtist().getArtistId());
                artistObj.put("artistName", info.getArtist().getArtistName());
                artistList.add(artistObj);
            }

            musicObj.put("artistList", artistList);
            musicList.add(musicObj);
        }

        this.artistList = new HashSet<>();
        for (MusicInfo info : entity.getInfos()) {
            Map<String, String> artistObj = new HashMap<>();
            artistObj.put("artistId", info.getArtist().getArtistId().toString());
            artistObj.put("artistName", info.getArtist().getArtistName());
            artistList.add(artistObj);
        }
    }
}
