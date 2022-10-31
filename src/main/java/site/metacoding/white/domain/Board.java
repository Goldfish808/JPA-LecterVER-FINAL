package site.metacoding.white.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 1000)
    private String content;

    // FK가 만들어짐. user_id

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 조회를 위해서만 필요함
    // CascadeType.ALL를 걸면 Board 객체 save시에 comment를 함께 저장할 수 있다.
    // 양방향 매핑시 걸어준다.
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Builder
    public Board(Long id, String title, String content, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // 변경하는 코드는 의미 있게 메서드로 구현
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
