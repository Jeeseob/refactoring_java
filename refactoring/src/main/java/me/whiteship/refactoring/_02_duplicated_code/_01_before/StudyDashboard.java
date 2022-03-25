package me.whiteship.refactoring._02_duplicated_code._01_before;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StudyDashboard {

    private void printParticipants(int eventId) throws IOException {
        GHIssue issue = getGhIssue(eventId);
        Set<String> participants = getUserNames(issue);
        print(participants);
    }

    private void printReviewers() throws IOException {
        GHIssue issue = getGhIssue(30);
        Set<String> reviewers = getUserNames(issue);
        print(reviewers);
    }

    // 코드를 블럭화하고, cmd+option+M을 누르면 함수로 만들어준다.
    private GHIssue getGhIssue(int eventId) throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        return repository.getIssue(eventId);
    }

    private Set<String> getUserNames(GHIssue issue) throws IOException {
        Set<String> userNames  = new HashSet<>();
        issue.getComments().forEach(c -> userNames.add(c.getUserName()));
        return userNames;
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
