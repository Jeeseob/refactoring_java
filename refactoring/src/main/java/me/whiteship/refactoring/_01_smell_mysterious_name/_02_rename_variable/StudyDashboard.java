package me.whiteship.refactoring._01_smell_mysterious_name._02_rename_variable;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 해결방법 2 : 변수 이름 변경하기 - rename variable
 *      더 많이 사용하는 변수 일 수록 이름이 중요하다.
 *          ex) 람다식에서 사용하는 변수 vs 함수의 매개변수
 *              - 람다식에서의 변수는 굉장히 작은 범주 내에서 사용되고, 대부분 코드만으로 해당 변수가 무엇인지 알 수 있어, 이름이 크게 중요하지 않다.
 *              - 함수의 매개변수는 외부에서 해당 함수를 사용하는 사람들이 이해할 수 있는 형태여야 하기 때문에 이름이 중요하다.
 *
 *      다이나믹 타입을 사용하는 언어에서는 변수명에 타입을 추가하기도 한다.
 *      여러 함수에서 사용되는 필드는 특히 이름에 대한 중요도가 높다.
 */

public class StudyDashboard {

    private Set<String> usernames = new HashSet<>();

    private Set<String> reviews = new HashSet<>();

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
            // 주석처리된 방법을 사용은 권장되지 않는다고 한다. (중요하진 않다)
//            this.usernames.add(review.getUserName());
            this.usernames.add(review.getUser().getName());
            this.reviews.add(review.getBody());
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
        studyDashboard.loadReviews();

        // 인텔리 제이에서 method reference로 변경 가능한 경우 추천을 해준다.(option + enter)
//        studyDashboard.getUsernames().forEach(n -> System.out.println(n));
//        studyDashboard.getReviews().forEach(r -> System.out.println(r));

        studyDashboard.getUsernames().forEach(System.out::println);
        studyDashboard.getReviews().forEach(System.out::println);
    }
}
