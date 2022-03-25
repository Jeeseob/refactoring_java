package me.whiteship.refactoring._01_smell_mysterious_name._01_change_method_declaration;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 해결방법 1 : 함수 선언 변경하기 - change function declaration
 *      함수명(선언부) 변경 / 매개변수 이름 변경 / 매개변수 추가 및 제거 등의 방법을 사용한다.
 *
 *      추천 방법 : 함수의 역할을 주석으로 작성한 다음, 주석을 기반으로 함수명을 만들어 본다.
 *
 *      함수의 매겨변수는 함수의 내부 문맥을 결정한다.
 *          ex) 문자열을 전화번호형태로 포매팅하는 함수
 *
 *              파라미터로 단순 텍스트만을 받을 건지,
 *                  - 단순 파라미터만 받는다면,
 *                  - 다른 객체의 전화번호라도 해당 함수를 활용할 수 있다.
 *                  - 타 객체에 대한 즉 재사용성은 좋을 수 있다.
 *
 *              전화번호를 포함하는 객체 오브젝트를 받을 것인지 등을 명확히 해야한다.
 *                  - 객체 오브젝트를 받는다면, 해당 오브젝트에 대한 모든 정보를 참조할 수 있다.
 *                  - 사람의 지역/국가 등에 따라 다른 포멧에 맞춰 포맷팅할 수 있다.(국가 번호 등을 추가 할 수도 있다.)
 *
 *          결론 : 정답은 없다.
 *
 *      함수의 매개변수는 의존성을 결정한다.
 *          객체를 넘그는 경우 의존성이 높다.
 *          객체에서 필요한 필드값만을 넘기는 경우 의존성이 낮다.
 *
 *          but -> 의존성이 낮은 것이 항상 옳다고 볼 순 없다.
 *                 상황에 따라 적절한 매개변수를 설정하는 것이 중요하다.
 **/

public class StudyDashboard {

    private Set<String> usernames = new HashSet<>();

    private Set<String> reviews = new HashSet<>();

    /**
     * 스터디 리뷰 이슈에서 유저명과 리뷰의 목록을 가져온다.
     * @throws IOException
     */
    private void loadRevies() throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        List<GHIssueComment> comments = issue.getComments();
        for (GHIssueComment comment : comments) {
            usernames.add(comment.getUserName());
            reviews.add(comment.getBody());
        }
    }

    public Set<String> getUsernames() {
        return usernames;
    }

    public Set<String> getReviews() {
        return reviews;
    }

    public static void main(String[] args) throws IOException {

        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.loadRevies();
        studyDashboard.getUsernames().forEach(System.out::println);
        studyDashboard.getReviews().forEach(System.out::println);
    }
}
