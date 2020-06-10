package wingmusic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wingmusic.domain.album.Album;
import wingmusic.domain.album.AlbumRepository;
import wingmusic.domain.artist.Artist;
import wingmusic.domain.artist.ArtistRepository;
import wingmusic.domain.music.Music;
import wingmusic.domain.music.MusicRepository;
import wingmusic.domain.musicInfo.MusicInfo;
import wingmusic.domain.musicInfo.MusicInfoRepository;
import wingmusic.web.dto.music.MusicResponseDto;
import wingmusic.web.dto.music.MusicSaveRequestDto;
import wingmusic.web.dto.music.MusicUpdateRequestDto;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("ALL")
@RequiredArgsConstructor
@Service
public class MusicService {

    private final MusicInfoService musicInfoService;
    private final MusicRepository musicRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final MusicInfoRepository musicInfoRepository;

    @Transactional
    public Long save(Long albumId, MusicSaveRequestDto requestDto) {

        Album toAlbum = albumRepository.findById(albumId).orElseThrow(
                () -> new IllegalArgumentException("해당 앨범 없음 id=" + albumId)
        );

        Music music = musicRepository.save(requestDto.toEntity(toAlbum));


        for(Long artistId : requestDto.getArtistIdList()) {
            Artist artist = artistRepository.findById(artistId).orElseThrow(
                    () -> new IllegalArgumentException("해당 아티스트 없음 id=" + artistId)
            );
            musicInfoService.save(artist, toAlbum, music);
        }

        return music.getMusicId();
    }

    @Transactional
    public Long update(Long id, MusicUpdateRequestDto requestDto) {
        Music music = musicRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("음악 정보 없음 id=" + id));


        music.update(requestDto.getMusicName(), requestDto.getMusicGenre(),
                requestDto.getFileUri(), requestDto.getTrackNumber(), requestDto.getLyrics());

        // 아티스트 목록에 변화가 있다면? 어떻게 처리해야 할까?
        // 1. 기존 참여 목록에 없던 아티스트를 추가하는 경우
        // 2. 기존 참여 목록에 있던 아티스트를 삭제하는 경우
        Set<MusicInfo> musicInfos = music.getInfos();

        // 기존 참여 목록에 있는 아티스트 ID
        Set<Long> artists = new HashSet<>();


        for (MusicInfo musicInfo : musicInfos)
            artists.add(musicInfo.getArtist().getArtistId());

        for (Long artistId : requestDto.getArtistIdList()) {
            System.out.println(artistId + " DTO 목록 아티스트");
            if (!artists.contains(artistId)) {
                System.out.println(artistId + " 추가");
                Artist addArtist = artistRepository.findById(artistId).orElseThrow(
                        () -> new IllegalArgumentException("아티스트 정보 없음 id=" + id)
                );
                musicInfoService.save(addArtist, music.getToAlbum(), music);
            }
        }

        for (Long artistId : artists) {
            System.out.println(artistId + " 기존 목록 아티스트");
            if (!requestDto.getArtistIdList().contains(artistId)) {
                System.out.println(artistId + " 삭제");
                Artist deleteArtist = artistRepository.findById(artistId).orElseThrow(
                        () -> new IllegalArgumentException("아티스트 정보 없음 id=" + id)
                );

                musicInfoService.delete(deleteArtist, music);
            }
        }

        return id;
    }

    @Transactional
    public Long delete(Long id) {
        Music music = musicRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("음악 정보 없음 id=" + id));

        musicRepository.delete(music);

        return id;
    }

    public MusicResponseDto findById(Long id) {
        Music music = musicRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("음악 정보 없음 id=" + id));

        return new MusicResponseDto(music);
    }

    public Set<MusicResponseDto> findByNameContaining(String name) {
        Set<Music> musicList = musicRepository.findByMusicNameContaining(name);
        Set<MusicResponseDto> responseDtos = new HashSet<>();

        for (Music music : musicList)
            responseDtos.add(new MusicResponseDto(music));

        return responseDtos;
    }

    public Set<MusicResponseDto> findByGenre(String genre) {
        Set<Music> musicList = musicRepository.findByMusicGenreContaining(genre);
        Set<MusicResponseDto> responseDtos = new HashSet<>();

        for (Music music : musicList)
            responseDtos.add(new MusicResponseDto(music));

        return responseDtos;
    }
}