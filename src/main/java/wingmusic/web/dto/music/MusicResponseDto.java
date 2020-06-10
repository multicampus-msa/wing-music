package wingmusic.web.dto.music;

import lombok.Getter;
import wingmusic.domain.music.Music;
import wingmusic.domain.musicInfo.MusicInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class MusicResponseDto {

    private final Long musicId;
    private final String musicName;
    private final String musicGenre;
    private final String fileUri;
    private final int trackNumber;
    private final String lyrics;
    private final Long likeCnt;
    private final Set<Map<String, String>> artistList;
    private final String albumImage;
    private final String albumName;
    private final Long albumId;

    public MusicResponseDto(Music entity) {
        this.musicId = entity.getMusicId();
        this.musicName = entity.getMusicName();
        this.musicGenre = entity.getMusicGenre();
        this.fileUri = entity.getFileUri();
        this.trackNumber = entity.getTrackNumber();
        this.lyrics = entity.getLyrics();
        this.likeCnt = entity.getLikeCnt();
        this.albumName = entity.getToAlbum().getAlbumName();
        this.albumImage = entity.getToAlbum().getImageUri();
        this.albumId = entity.getToAlbum().getAlbumId();
        this.artistList = new HashSet<>();

        for(MusicInfo info : entity.getInfos()) {
            Map<String, String> artistObj = new HashMap<>();
            artistObj.put("artistId", info.getArtist().getArtistId().toString());
            artistObj.put("artistName", info.getArtist().getArtistName());
            artistList.add(artistObj);
        }
    }
}
