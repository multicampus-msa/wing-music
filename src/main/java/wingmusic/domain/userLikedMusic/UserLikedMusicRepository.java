package wingmusic.domain.userLikedMusic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wingmusic.domain.music.Music;
import wingmusic.domain.user.User;

import java.util.Set;

@Repository
public interface UserLikedMusicRepository extends JpaRepository<UserLikedMusic, Long> {
    UserLikedMusic findByUserAndMusic(User user, Music music);
}
