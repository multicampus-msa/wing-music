package wingmusic.web.dto.artist;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Getter
public class ArtistUpdateRequestDto {

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
    private List<Long> albumIdList;

    @Builder
    public ArtistUpdateRequestDto(String artistName, String artistCompany, String artistGenre,
                                  Date debutDate, String video, String imageUri,
                                  String description, String realName, String account, String bank) {

        this.artistName = artistName;
        this.artistCompany = artistCompany;
        this.artistGenre = artistGenre;
        this.debutDate = debutDate;
        this.imageUri = imageUri;
        this.video = video;
        this.description = description;
        this.realName = realName;
        this.account = account;
        this.bank = bank;
    }

}
