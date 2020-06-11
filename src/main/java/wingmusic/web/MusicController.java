package wingmusic.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wingmusic.service.MusicService;
import wingmusic.service.UserService;
import wingmusic.web.dto.music.MusicResponseDto;
import wingmusic.web.dto.music.MusicSaveRequestDto;
import wingmusic.web.dto.music.MusicUpdateRequestDto;
import wingmusic.web.dto.user.UserLikedMusicRequestDto;
import wingmusic.web.dto.user.UserLikedMusicResponseDto;

import java.util.Set;


@Api(tags = {"Music Controller"})
@RestController
@AllArgsConstructor
@CrossOrigin
public class MusicController {

    private final UserService userService;

    private final MusicService musicService;

    @ApiOperation(value = "음악 조회", notes = "기본 키 (music_id)로 조회")
    @GetMapping("/api/music/{id}")
    private MusicResponseDto findById(@PathVariable("id") Long id) {
        return musicService.findById(id);
    }

    @ApiOperation(value = "음악 검색", notes = "이름으로 검색 후 JSON 리스트로 반환. name 파라미터 필요.")
    @GetMapping("/api/music/name={name}")
    private Set<MusicResponseDto> findByName(@PathVariable("name") String name){
        return musicService.findByNameContaining(name);
    }

    @ApiOperation(value = "음악 등록", notes = "음악 등록(artist_id, album_id 필요)")
    @PostMapping("/api/music")
    private Long save(@RequestParam Long artistId, @RequestParam Long albumId,
                      @RequestBody MusicSaveRequestDto requestDto) {

        return musicService.save(albumId, requestDto);
    }

    @ApiOperation(value = "음악 수정", notes = "음악 수정")
    @PutMapping("/api/music/{id}")
    private Long update(@PathVariable Long id, @RequestBody MusicUpdateRequestDto requestDto) {
        return musicService.update(id, requestDto);
    }

    @ApiOperation(value = "음악 삭제", notes = "음악 삭제")
    @DeleteMapping("api/music/{id}")
    private Long delete(@PathVariable Long id) { return musicService.delete(id); }

    @ApiOperation(value = "음악 장르별 검색", notes = "장르별로 검색하기")
    @GetMapping("/api/music/genre={genre}")
    private Set<MusicResponseDto> findByGenre(@PathVariable("genre") String genre) {
        return musicService.findByGenre(genre);
    }


    @ApiOperation(value = "유저 음악 좋아요")
    @PostMapping("/api/music/liked")
    public Long userLikedMusicSave(@RequestBody UserLikedMusicRequestDto requestDto){
        return userService.likedMusic(requestDto);
    }

    @ApiOperation(value = "유저 음악 좋아요 취소")
    @DeleteMapping("/api/music/liked")
    public Long userLikedMusicDelete(@RequestBody UserLikedMusicRequestDto requestDto){
        return userService.likedMusicDelete(requestDto);
    }

    @ApiOperation(value = "유저 음악 좋아요 목록")
    @GetMapping("/music/liked/{id}")
    public UserLikedMusicResponseDto userLikedMusicSave(@PathVariable("id") String userId){
        return userService.likedMusicSet(userId);
    }
}
