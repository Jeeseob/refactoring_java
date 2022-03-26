package me.whiteship.refactoring._01_smell_mysterious_name._03_rename_field;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**  해결방법 3 : 필드 이름 변경하기 - rename field
 *      필드명은 프로그램 전반에 걸쳐 사용될 수 있기 때문에 더욱 중요하다.
 *      Record로 필요한 데이터를 하나의 필드로 묶는 것도 방법이다.
 */

public class StudyDashboard {

    private Set<StudyReview> studyReviews = new HashSet<>();

    /**
     * 스터디 리뷰 이슈에 작성되어 있는 리뷰어 목록과 리뷰를 읽어옵니다.
     * @throws IOException
     */
    private void loadReviews() throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        List<GHIssueComment> reviews = issue.getComments();
        for (GHIssueComment review : reviews) {
            studyReviews.add(new StudyReview(review.getUser().getName(), review.getBody()));
        }
    }

    public Set<StudyReview> getStudyReviews() {
        return studyReviews;
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.loadReviews();
        // toString을 통해 출력한다.
        studyDashboard.getStudyReviews().forEach(System.out::println);
    }
}
