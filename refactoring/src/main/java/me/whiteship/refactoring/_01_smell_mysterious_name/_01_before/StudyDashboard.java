package me.whiteship.refactoring._01_smell_mysterious_name._01_before;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// 문제점 1 : 이해하기 힘든 이름

// 해결방법 :

// 함수 선언 변경하기 - change function declaration

// 함수의 선언부를 변경하는 리팩토링 방식
// 함수명(선언부) 변경 / 매개변수 이름 변경 / 매개변수 추가 및 제거 등
// 함수의 역할을 주석으로 작성한 다음, 주석을 함수 명으로 만들어 본다.

// 함수의 매겨변수는 함수의 내부 문맥을 결정한다. --> ex) 문자열을 전화번호형태로 포매팅하는 함수라고 한다면, 파라미터로 단순 텍스트만을 받을 건지, 전화번호를 포함하는 객체 오브젝트를 받을 것인지 등을 명확히 해야한다.
// 단순 파라미터만 받는다면, 재사용성은 좋을 수 있다. / 객체 오브젝트를 받는다면, 사람의 지역/국가 등에 따라 다른 포멧에 맞춰 포맷팅할 수 있다.(국가 번호 등을 추가 할 수도 있다.) --> 정답은 없다.

// 매개 변수는 의존성을 결정한다. --> ex)


// 변수 이름 바꾸기 - rename variable
// 필드 이름 바꾸기 - rename field
public class StudyDashboard {

    private Set<StudyReview> studyReviews = new HashSet<>();

    /**
     * 스터디 리뷰 이슈에 작성되어 있는 유저의 목록과 리뷰들을 읽어옵니다.
     * @throws IOException
     */
    // 외부에서 읽어오는 것은 load를 사용하는 것이 맞을 듯.
    private void loadReviews() throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(30);

        // comments가 타입적으로는 맞는 형태지만, 실제 데이터는 review들이고, 해당 클래스에 필드명이 reviews이기 때문에 reiviews로 변경하는 것이 좋을 수 있다.
        List<GHIssueComment> reviews = issue.getComments();
        for (GHIssueComment review : reviews) {
            // github 라이브러리에서 getUserName은 현재 권장되지 않는 방식/ getUser().getLogin()이 권장됨.
            studyReviews.add(new StudyReview(review.getUser().getLogin(), review.getBody()));
        }
    }

    public Set<StudyReview> getStudyReviews() {
        return studyReviews;
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.loadReviews();
        // 람다식은 범위가 굉장히 좁고, 이미 어떤 type의 어떤 변수인지 알고 있기 때문에, 간추려서 쓰거나, 없애기도 한다.
        // 특히 method reference로 변경이 가능한 경우 인텔리제이에서 추천해준다.
        studyDashboard.getStudyReviews().forEach(System.out::println);
    }
}
