package wingmusic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wingmusic.domain.music.Music;
import wingmusic.domain.music.MusicRepository;
import wingmusic.domain.user.User;
import wingmusic.domain.user.UserRepository;
import wingmusic.domain.userLikedMusic.UserLikedMusic;
import wingmusic.domain.userLikedMusic.UserLikedMusicRepository;
import wingmusic.web.dto.user.UserLikedMusicRequestDto;
import wingmusic.web.dto.user.UserLikedMusicResponseDto;
import wingmusic.web.dto.user.UserRequestDto;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final MusicRepository musicRepository;
    private final UserLikedMusicRepository userLikedMusicRepository;
    private final UserLikedMusicService userLikedMusicService;

    @Transactional
    public Long likedMusic(UserLikedMusicRequestDto requestDto) {

        // UserLikedMusicRepo 에 저장하기
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

        userLikedMusicService.save(user, music);

        return music.getMusicId();
    }

    public UserLikedMusicResponseDto likedMusicSet(String userId) {

        User user;
        Optional<User> opt = userRepository.findById(userId);

        if (!opt.isPresent()) {
            user = new User(userId);
            userRepository.save(user);
        }
        else user = opt.get();

        return new UserLikedMusicResponseDto(user);

    }

    @Transactional
    public Long likedMusicDelete(UserLikedMusicRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));

        Music music = musicRepository.findById(requestDto.getMusicId())
                .orElseThrow(() -> new IllegalArgumentException("해당 음악 없음"));

        UserLikedMusic likedMusic = userLikedMusicRepository.findByUserAndMusic(user, music);
        userLikedMusicRepository.delete(likedMusic);

        return requestDto.getMusicId();
    }
}
