package wingmusic.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wingmusic.domain.userLikedMusic.UserLikedMusic;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
public class User {

    @Id
    private String userId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<UserLikedMusic> likedMusicSet = new HashSet<>();

    public User(String userId) {
        this.userId = userId;
    }

    public void likedMusicUpdate(Set<UserLikedMusic> likedMusicSet) {
        this.likedMusicSet = new HashSet<>(likedMusicSet);
    }
}
