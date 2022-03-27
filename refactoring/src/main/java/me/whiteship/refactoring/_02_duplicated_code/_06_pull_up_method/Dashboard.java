package me.whiteship.refactoring._02_duplicated_code._06_pull_up_method;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/** 해결방법 3 : 메소드 올리기 - pull up method
 *
 *      하위 클래스에서 중복되는 메소드들을 상위 클래스로 올리는 리팩토링 방법
 *          -> 중복코드를 제거하는 방법이다.
 *          -> 완전히 동일하지 않은 코드라도, 함수 매개변수화 하기/필드 올리기 등 다른 리팩토링 기법과 함께 사용하면 메소드 올리기가 가능하다.
 *
 *      사용 방법
 *          -> method를 수정해서 동일하게 만든다.
 *          -> refactor의 pull Members up을 사용한다.
 */

public class Dashboard {

    public static void main(String[] args) throws IOException {
        ReviewerDashboard reviewerDashboard = new ReviewerDashboard();
        reviewerDashboard.printReviewers();

        ParticipantDashboard participantDashboard = new ParticipantDashboard();
        participantDashboard.printUserNames(15);
    }

    public void printUserNames(int eventId) throws IOException {
        // Get github issue to check homework
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);

        // Get participants
        Set<String> participants = new HashSet<>();
        issue.getComments().forEach(c -> participants.add(c.getUserName()));

        // Print participants
        participants.forEach(System.out::println);
    }
}
