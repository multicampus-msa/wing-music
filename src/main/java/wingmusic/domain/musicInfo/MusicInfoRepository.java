package wingmusic.domain.musicInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MusicInfoRepository extends JpaRepository<MusicInfo, Long> {

    Set<MusicInfo> findByAlbum_AlbumId(Long id);
    Set<MusicInfo> findByMusic_MusicId(Long id);

}
