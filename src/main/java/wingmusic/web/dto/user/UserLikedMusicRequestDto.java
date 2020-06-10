package wingmusic.web.dto.user;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLikedMusicRequestDto {

    private String userId;
    private Long musicId;


    public UserLikedMusicRequestDto(String userId, Long musicId) {
        this.userId = userId;
        this.musicId = musicId;
    }

}
