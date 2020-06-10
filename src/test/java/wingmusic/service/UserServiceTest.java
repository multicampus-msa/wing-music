package wingmusic.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wingmusic.domain.music.Music;
import wingmusic.domain.music.MusicRepository;
import wingmusic.domain.user.User;
import wingmusic.domain.user.UserRepository;
import wingmusic.domain.userLikedMusic.UserLikedMusicRepository;
import wingmusic.web.dto.user.UserLikedMusicRequestDto;
import wingmusic.web.dto.user.UserLikedMusicResponseDto;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private UserLikedMusicRepository userLikedMusicRepository;

    @Autowired
    private UserLikedMusicService userLikedMusicService;

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void likedMusic() {

        UserLikedMusicRequestDto requestDto = new UserLikedMusicRequestDto("유저", 4L);

        Music music = musicRepository.findById(requestDto.getMusicId())
                .orElseThrow(() -> new IllegalArgumentException("해당 음악 없음"));


        User user;

        Optional<User> opt = userRepository.findById(requestDto.getUserId());

        if (!opt.isPresent()) {
            user = new User(requestDto.getUserId());
            userRepository.save(userRepository.save(user));
        }
        else
            user = opt.get();


        assertEquals(user.getUserId(), "유저");
        assertEquals(music.getMusicId(), 4L);
        userLikedMusicService.save(user, music);

        assertSame(userLikedMusicRepository.findByUserAndMusic(user, music).getUser().getUserId(), "유저");

    }

    @Test
    void likedMusicSet() {
        User user;
        Optional<User> opt = userRepository.findById("HELLO");

        if (!opt.isPresent()) {
            user = new User("HELLO");
            userRepository.save(user);
        }
        else user = opt.get();

        assertEquals(new UserLikedMusicResponseDto(user).getMusicIdSet().size(), 0);
    }

    @Test
    @Transactional
    void likedMusicDelete() {
    }
}