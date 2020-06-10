package wingmusic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wingmusic.domain.music.Music;
import wingmusic.domain.user.User;
import wingmusic.domain.userLikedMusic.UserLikedMusic;
import wingmusic.domain.userLikedMusic.UserLikedMusicRepository;


@RequiredArgsConstructor
@Service
public class UserLikedMusicService {

    private final UserLikedMusicRepository userLikedMusicRepository;

    @Transactional
    public void save(User user, Music music) {

        UserLikedMusic likedMusic = userLikedMusicRepository.save(UserLikedMusic.builder()
                .user(user)
                .music(music)
                .build()
        );
    }

}
