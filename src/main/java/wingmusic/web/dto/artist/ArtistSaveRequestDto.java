package wingmusic.web.dto.artist;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wingmusic.domain.artist.Artist;

import java.sql.Date;

@NoArgsConstructor
@Getter
public class ArtistSaveRequestDto {

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

    @Builder
    public ArtistSaveRequestDto(String artistName, String artistCompany, String artistGenre,
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

    public Artist toEntity() {
        return Artist.builder()
                .artistName(artistName)
                .artistCompany(artistCompany)
                .artistGenre(artistGenre)
                .debutDate(debutDate)
                .video(video)
                .imageUri(imageUri)
                .description(description)
                .realName(realName)
                .account(account)
                .bank(bank)
                .build();
    }
}
