package wingmusic.web.dto.album;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wingmusic.domain.album.Album;

import java.sql.Date;

@NoArgsConstructor
@Getter
public class AlbumSaveRequestDto {

    private String albumName;
    private String albumGenre;
    private String company;
    private String distributor;
    private Date date;
    private String imageUri;
    private String description;

    @Builder
    public AlbumSaveRequestDto(String albumName, String albumGenre, String company,
                               String distributor, Date date, String imageUri, String description) {

        this.albumName = albumName;
        this.albumGenre = albumGenre;
        this.company = company;
        this.distributor = distributor;
        this.date = date;
        this.imageUri = imageUri;
        this.description = description;
    }

    public Album toEntity() {
        return Album.builder()
                .albumName(albumName)
                .albumGenre(albumGenre)
                .company(company)
                .distributor(distributor)
                .date(date)
                .imageUri(imageUri)
                .description(description)
                .build();
    }
}
