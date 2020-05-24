# Ändringslogg #

Noterbara ändringar som gjorts för detta projekt dokumenteras i denna fil.

## 2020-05-24 ##
- Gjorde arbete på följande mallar:
  - `askSoundQuestion.html`
- Fixade en bugg i metoden `getQuestion()` (PlayController)

## 2020-05-23 ##
- Lade till kod för att kontrollera storlek på upload (säkerhet)
- Fixade bättre validering av input för:
  - `/addTextQuestion`
  - `/addSoundQuestion`
  - `/addImageQuestion`
- Lade till följande metoder i klassen Utilities:
  - `zipBytes()`
  - `unzipBytes()`
  - `getSoundFileMaxSizeString()`
  - `getImageFileMaxSizeString()`
- Ljud- och bildfrågor sparas nu komprimerade i databasen
- Gjorde arbete på följande mallar:
  - `playQuiz.html`
- Lade till följande mallar:
  - `askTextQuestion.html`
  - `askSoundQuestion.html`
  - `askImageQuestion.html`
- Fixade mapping för:
  - `/askQuestion`
- Flyttade "play functionality" till en egen kontroller

## 2020-05-22 ##
- Lade till GetMapping för:
  - `/playQuiz`
- Lade till följande mallar:
  - `playQuiz.html`
- Lade till kod till klassen `Question`
- Lade till följande metoder i klassen MainController:
  - `getQuestion()`
  - `getAllQuestionsForQuiz()`

## 2020-05-21 ##
- Databasen lagrar nu även:
  - Ljudfrågor
  - Bildfrågor

## 2020-05-20 ##
- Lade till följande mallar:
  - `doneWithQuestions.html`
  - `quizAlreadyExists.html`
- Lade till nya stilar
- Lade till följande metoder i klassen MainController:
  - `getQuizUniqueId()`
  - `getQuizByUniqueId()`
- Lade till PostMapping för:
  - `/addTextQuestion`
  - `/addSoundQuestion`
  - `/addImageQuestion`
- Databasen lagrar nu:
  - Textfrågor

## 2020-05-19 ##
Arbetat med att föra in textfrågor för ett specifikt quiz i databasen.
Ej klar.

## 2020-05-18 ##
- Lade till följande metoder i klassen MainController:
  - `getQuizId()`
  - `getQuiz()`
  - `addQuestion()`
- Lade till följande metoder i klassen Utilities:
  - `intToQuestionType`
- Lade till följande klasser:
  - `Question`
  - `QuestionText`
  - `QuestionSound`
  - `QuestionImage`
- Lade till följande mallar:
  - `addTextQuestion.html`
  - `addSoundQuestion.html`
  - `addImageQuestion.html`
- Lade till nya fragment
- Lade till följande förvar:
  - QuestionTextRepo
  - QuestionSoundRepo
  - QuestionImageRepo

## 2020-05-17 ##
Startsidan listar nu quiz och det är möjligt att filtrera visningen med hjälp
av att ange ämne/kategori och språk.

I nuläget när man trycker på "Create new quiz" får man ange
    1. Titel
    2. Ämne/kategori
    3. Vilket språk frågorna i quizet kommer vara på

När detta är gjort finns det en knapp "Ok, start adding questions".
I samband med att trycka på knappen förs tillgänglig data in i databasen.
Tanken är sedan att användaren ska få börja lägga till frågor och svar,
men denna funktionalitet är inte skriven ännu.

## 2020-05-16 ##
- Lade till följande metoder i klassen Utilities
  - `topicToString()`
  - `languageToString()`

## 2020-05-15 ##
Arbetat med visning av startsidan som skall lista quiz.
