package wingmusic.web.dto.album;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Getter
public class AlbumUpdateRequestDto {

    private String albumName;
    private String albumGenre;
    private String company;
    private String distributor;
    private Date date;
    private String imageUri;
    private String description;

    @Builder
    public AlbumUpdateRequestDto(String albumName, String albumGenre, String company,
                                 String distributor, Date date, String imageUri, String description) {
        this.albumName = albumName;
        this.albumGenre = albumGenre;
        this.company = company;
        this.distributor = distributor;
        this.date = date;
        this.imageUri = imageUri;
        this.description = description;
    }
}
