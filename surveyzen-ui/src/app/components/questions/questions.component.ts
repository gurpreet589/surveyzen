import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { Observable } from "rxjs";

@Component({
  selector: "app-questions",
  templateUrl: "./questions.component.html",
  styleUrls: ["./questions.component.css"],
})
export class QuestionsComponent {
  questions: any[] = [];
  constructor(private http: HttpClient) {}
  ngOnInit(): void {
    const session = sessionStorage.getItem("TEXT");
    console.log("from session", session);
    this.questions = [
      {
        id: "1",
        type: "text",
        questionText: "My question 1",
        answer: {
          ans1: "answer1",
          ans2: "answer2",
          ans3: "answer3",
        },
      },
      {
        id: "2",
        type: "multipleChoice",
        questionText: "My question 2",

        answers: [
          {
            id: "a1",
            answerText: "answer text 1",
          },
          {
            id: "a2",
            answerText: "answer text 2",
          },
        ],
      },
    ];
    this.getQuestions().subscribe((data: any) => {
      this.questions = data;
    });
  }
  getQuestions(): Observable<any[]> {
    return this.http.get<any[]>("http://localhost:8089/questions");
  }
  submitAnswers(answers: any[]): Observable<any> {
    return this.http.post<any>("http://localhost:8089/questions", answers);
  }
  submit(): void {
    const answers = [];
    for (const question of this.questions) {
      const answer: any = {};
      answer["questionId"] = question.id;
      if (question.type === "multipleChoice") {
        const selectedAnswer = question.answers.find((a: any) => {
          const element = document.querySelector(
            `input[name="${question.id}"][value="${a.id}"]`
          ) as HTMLInputElement;
          return element.checked;
        });
        answer["answerId"] = selectedAnswer.id;
      }
      if (question.type === "text") {
        const element = document.querySelector(
          `input[name="${question.id}"]`
        ) as HTMLInputElement;
        answer["answerText"] = element.value;
      }
      answers.push(answer);
      const answerToSession = JSON.stringify(answers);
      sessionStorage.setItem("TEXT", answerToSession);

      console.log(answers);
    }
    this.submitAnswers(answers).subscribe((response: any) => {
      console.log(response);
    });
  }
}
