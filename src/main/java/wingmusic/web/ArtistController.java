package wingmusic.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wingmusic.service.ArtistService;
import wingmusic.web.dto.artist.ArtistResponseDto;
import wingmusic.web.dto.artist.ArtistSaveRequestDto;

import java.util.List;

@Api(tags = {"Artist Controller"})
@RestController
@AllArgsConstructor
@CrossOrigin
public class ArtistController {

    private final ArtistService artistService;

    @ApiOperation(value = "아티스트 조회", notes = "기본 키 (artist_id)로 조회")
    @GetMapping("/api/artist/{id}")
    private ArtistResponseDto findById(@PathVariable("id") Long id) {
        return artistService.findById(id);
    }


    @ApiOperation(value = "아티스트 검색", notes = "이름으로 검색 후 JSON 리스트로 반환. name 파라미터 필요.")
    @GetMapping("/api/artist")
    private List<ArtistResponseDto> findByName(@RequestParam("name") String name) {
        return artistService.findByNameContaining(name);
    }


    @ApiOperation(value = "아티스트 등록", notes = "아티스트 등록")
    @PostMapping("/api/artist")
    private Long save(@RequestBody ArtistSaveRequestDto requestDto) {
        return artistService.save(requestDto);
    }


    @ApiOperation(value = "아티스트 수정", notes = "아티스트 수정")
    @PutMapping("/api/artist/{id}")
    private Long update(@PathVariable Long id, @RequestBody ArtistSaveRequestDto requestDto) {
        return artistService.update(id, requestDto);
    }


    @ApiOperation(value = "아티스트 삭제", notes = "아티스트 삭제")
    @DeleteMapping("api/artist/{id}")
    private Long delete(@PathVariable Long id) { return artistService.delete(id); }

}
