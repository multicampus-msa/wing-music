package wingmusic.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wingmusic.domain.user.User;

// 구글에서 전달받은 정보를 담을 Dto
@Getter
@NoArgsConstructor
public class UserRequestDto {

    private String id;

    @Builder
    private UserRequestDto(String id){
        this.id=id;
    }

    public User toEntity(){
        return new User(id);
    }
}
