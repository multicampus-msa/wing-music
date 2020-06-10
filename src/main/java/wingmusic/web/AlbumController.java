package wingmusic.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wingmusic.service.AlbumService;
import wingmusic.web.dto.album.AlbumResponseDto;
import wingmusic.web.dto.album.AlbumSaveRequestDto;
import wingmusic.web.dto.album.AlbumUpdateRequestDto;

import java.util.List;

@Api(tags = {"Album Controller"})
@RestController
@AllArgsConstructor
@CrossOrigin
public class AlbumController {

    private final AlbumService albumService;

    @ApiOperation(value = "앨범 조회", notes = "기본 키 (album_id)로 조회")
    @GetMapping("/api/album/{id}")
    private AlbumResponseDto findById(@PathVariable("id") Long id) {
        return albumService.findById(id);
    }


    @ApiOperation(value = "앨범 검색", notes = "이름으로 검색 후 JSON 리스트로 반환. name 파라미터 필요.")
    @GetMapping("/api/album")
    private List<AlbumResponseDto> findByName(@RequestParam("name") String name) {
        return albumService.findByNameContaining(name);
    }


    @ApiOperation(value = "앨범 등록", notes = "앨범 등록")
    @PostMapping("/api/album")
    private Long save(@RequestBody AlbumSaveRequestDto requestDto) {
        return albumService.save(requestDto);
    }


    @ApiOperation(value = "앨범 수정", notes = "앨범 수정")
    @PutMapping("/api/album/{id}")
    private Long update(@PathVariable Long id, @RequestBody AlbumUpdateRequestDto requestDto) {
        return albumService.update(id, requestDto);
    }


    @ApiOperation(value = "앨범 삭제", notes = "앨범 삭제")
    @DeleteMapping("api/album/{id}")
    private Long delete(@PathVariable Long id) { return albumService.delete(id); }

}
