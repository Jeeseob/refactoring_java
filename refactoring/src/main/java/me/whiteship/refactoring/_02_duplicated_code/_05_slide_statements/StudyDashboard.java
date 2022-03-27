package me.whiteship.refactoring._02_duplicated_code._05_slide_statements;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/** 해결방법 2 : 코드 정리하기 - slid statements
 *
 *      관련있는 코드들을 모아두는 것이 좋다.
 *         ex) 함수 내부에서 사용하는 변수는, 함수를 사용하는 코드 직전에 선언하는 것이 좋다.
 *              -> 코드를 변수와 함꼐 뭉쳐서 볼 수 있다.
 *              -> 코드를 순서대로 인식이 가능하다.
 *
 *      * Tips
 *          1. shift + optioin + (방향키)위/아래
 *              -> 코드를 위아래로 줄단위/블럭 단위로 옮길 수 있다.
 *          2. shift + cmd + (방향키)오른쪽/왼쪽
 *              -> 코드의 맨끝으로 한번에 이동할 수 있다.
 *          3. shift + option + (방향키)오른쪽/왼쪽
 *              -> 코드를 단어 단위로 한번에 이동할 수 있다.
 */

public class StudyDashboard {

    private void printParticipants(int eventId) throws IOException {
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

    private void printReviewers() throws IOException {
        // Get github issue to check homework
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        // Get reviewers
        Set<String> reviewers = new HashSet<>();
        issue.getComments().forEach(c -> reviewers.add(c.getUserName()));

        // Print reviewers
        reviewers.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.printReviewers();
        studyDashboard.printParticipants(15);
    }
}
