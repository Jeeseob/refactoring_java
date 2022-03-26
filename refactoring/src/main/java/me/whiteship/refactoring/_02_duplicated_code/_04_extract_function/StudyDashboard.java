package me.whiteship.refactoring._02_duplicated_code._04_extract_function;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/** 해결방법 1 : 함수 추출하기 - extract function
 *      의도와 구현을 구분하면서 코드를 개발하자.
 *          의도 - 함수를 보면서, 함수의 진행을 바로 알 수 있도록
 *          구현 - 기능을  구현(한번에 알아보기 어려울 수도 있음)
 *
 *          - 함수를 추출할 때의 장점은 함수에 이름을 줄 수 있다는 것이다.
 *          - 의도를 명확히 하는 함수명을 줄 수 있다면, 한줄짜리 코드를 함수로 분리하는 것도 괜찮은 방법이다.
 */

public class StudyDashboard {

    private void printParticipants(int eventId) throws IOException {
        GHIssue issue = loadGHIssue(eventId);
        Set<String> participants = getUserNames(issue);
        print(participants);
    }

    private void printReviewers() throws IOException {
        GHIssue issue = loadGHIssue(30);
        Set<String> reviewers = getUserNames(issue);
        print(reviewers);
    }

    // 코드를 블럭화하고, cmd+option+M을 누르면 함수로 만들어준다.
    private GHIssue loadGHIssue(int i) throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        return repository.getIssue(i);
    }

    private Set<String> getUserNames(GHIssue issue) throws IOException {
        Set<String> reviewers = new HashSet<>();
        issue.getComments().forEach(c -> reviewers.add(c.getUserName()));
        return reviewers;
    }

    private void print(Set<String> participants) {
        participants.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.printReviewers();
        studyDashboard.printParticipants(15);
    }

}
