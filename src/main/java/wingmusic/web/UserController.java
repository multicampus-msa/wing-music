package wingmusic.web;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wingmusic.service.UserService;
import wingmusic.web.dto.user.UserLikedMusicRequestDto;
import wingmusic.web.dto.user.UserLikedMusicResponseDto;

@RestController
@Api(tags = {"User Controller"})
@RequestMapping(value = "/api/user")
@CrossOrigin
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    @ApiOperation(value = "유저 음악 좋아요")
    @PostMapping("/liked")
    public Long userLikedMusicSave(@RequestBody UserLikedMusicRequestDto requestDto){
        return userService.likedMusic(requestDto);
    }

    @ApiOperation(value = "유저 음악 좋아요 취소")
    @DeleteMapping("/liked")
    public Long userLikedMusicDelete(@RequestBody UserLikedMusicRequestDto requestDto){
        return userService.likedMusicDelete(requestDto);
    }

    @ApiOperation(value = "유저 음악 좋아요 목록")
    @GetMapping("/liked/{id}")
    public UserLikedMusicResponseDto userLikedMusicSave(@PathVariable("id") String userId){
        return userService.likedMusicSet(userId);
    }
}
