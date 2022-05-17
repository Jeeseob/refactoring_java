package me.whiteship.refactoring._03_long_function._10_replace_function_with_command;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** refactoring 10 : 함수를 명령으로 바꾸기 - replace function whit command
 *      함수를 command로 만들어 활용(command pattern에서의 command를 의미)
 *      Command로 operation을 빼낸다.
 *          - Undo 기능을 구현할 수 있다.
 *
 *      함수를 분리해도, 해당 함수의 위치가 좋지 않다면,
 *      별도의 Class로 분리하는 것도 방법이다.
 **/

public class StudyDashboard {

    private final int totalNumberOfEvents;

    public StudyDashboard(int totalNumberOfEvents) {
        this.totalNumberOfEvents = totalNumberOfEvents;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        StudyDashboard studyDashboard = new StudyDashboard(15);
        studyDashboard.print();
    }

    private Participant findParticipant(String username, List<Participant> participants) {
        Participant participant = null;
        if (participants.stream().noneMatch(p -> p.username().equals(username))) {
            participant = new Participant(username);
            participants.add(participant);
        } else {
            participant = participants.stream().filter(p -> p.username().equals(username)).findFirst().orElseThrow();
        }
        return participant;
    }

    private void print() throws IOException, InterruptedException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        List<Participant> participants = new CopyOnWriteArrayList<>();

        ExecutorService service = Executors.newFixedThreadPool(8);
        CountDownLatch latch = new CountDownLatch(totalNumberOfEvents);

        for (int index = 1 ; index <= totalNumberOfEvents ; index++) {
            int eventId = index;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        GHIssue issue = repository.getIssue(eventId);
                        List<GHIssueComment> comments = issue.getComments();

                        for (GHIssueComment comment : comments) {
                            Participant participant = findParticipant(comment.getUserName(), participants);
                            participant.setHomeworkDone(eventId);
                        }

                        latch.countDown();
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            });
        }

        latch.await();
        service.shutdown();

        new StudyPrinter(this.totalNumberOfEvents, participants).excute();
    }
}
