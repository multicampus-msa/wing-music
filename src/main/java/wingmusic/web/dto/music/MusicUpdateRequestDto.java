package wingmusic.web.dto.music;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class MusicUpdateRequestDto {

    private String musicName;
    private String musicGenre;
    private String fileUri;
    private int trackNumber;
    private String lyrics;
    private List<Long> artistIdList;

    @Builder
    public MusicUpdateRequestDto(String musicName, String musicGenre, String fileUri,
                                 int trackNumber, String lyrics, List<Long> artistIdList) {

        this.musicName = musicName;
        this.musicGenre = musicGenre;
        this.fileUri = fileUri;
        this.trackNumber = trackNumber;
        this.lyrics = lyrics;
        this.artistIdList = new ArrayList<>(artistIdList);
    }
}
