package wingmusic.web.dto.user;

import lombok.Getter;
import wingmusic.domain.musicInfo.MusicInfo;
import wingmusic.domain.user.User;
import wingmusic.domain.userLikedMusic.UserLikedMusic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class UserLikedMusicResponseDto {

    private final Set<Map<Object, Object>> musicSet;
    private final Map<Long, Long> musicIdSet;

    public UserLikedMusicResponseDto(User entity) {

        this.musicSet = new HashSet<>();
        this.musicIdSet = new HashMap<>();

        for(UserLikedMusic likedMusic : entity.getLikedMusicSet()) {
            Map<Object, Object> musicObj = new HashMap<>();
            musicObj.put("musicId", likedMusic.getMusic().getMusicId().toString());
            musicObj.put("musicName", likedMusic.getMusic().getMusicName());
            musicObj.put("fileUri",likedMusic.getMusic().getFileUri());


            Set<Object> artistList = new HashSet<>();

            for (MusicInfo info : likedMusic.getMusic().getInfos()) {
                Map<Object, Object> artistObj = new HashMap<>();
                artistObj.put("artistId", info.getArtist().getArtistId());
                artistObj.put("artistName", info.getArtist().getArtistName());
                artistList.add(artistObj);
            }

            musicObj.put("artistList", artistList);
            musicIdSet.put(likedMusic.getMusic().getMusicId(), 1L);
            musicSet.add(musicObj);
        }
    }
}
