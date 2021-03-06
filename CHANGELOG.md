# Ändringslogg #

Noterbara ändringar som gjorts för detta projekt dokumenteras i denna fil.

## [1.1.3] - 2020-07-15 ##
- Fixade en bugg som uppstod när ett eller flera svarsalternativ
  innehöll citattecken (JSON-relaterat)
- Lade till en stängknapp för att komma tillbaka till startsidan vid
  avslutat quiz

## [1.1.2] - 2020-07-13 ##
- Lade till @Size annotation för question

## [1.1.1] - 2020-07-12 ##
- Lade till mapping för:
  - `/bhABhfEwOiH723CIzLeSTo781rBUbQZs_AdminView`
- Lade till följande mallar:
  - `adminView.html`
- questionMaxLen: 200 -> 400
- Begränsade bredd för fråga

## 2020-07-03 ##
- Lade till `standalone/AdminViewUrl.cpp`

## [1.1.0] - 2020-07-01 ##
- Vid rapportering av frågor så är det nu möjligt att konfigurera
  till/från adress i `application.properties`.
- Förbättrade förhandsgranskning av bild vid bildfråga
- Fixade en bugg som berodde på att quizkatalogen för ett specifikt
  quiz inte fanns på hårddisken
- Lade till följande filer:
  - `Admin.java`
- Ett lösenord måste nu anges för att kunna skapa nya quiz

## 2020-06-30 ##
- Fixade visning av rapportformulär vid låg upplösning
- Lade till följande filer:
  - `radioButton.css`
- Fixade användning av radioknappar för frågor som enbart har ett rätt svar.
- UUID checking

## 2020-06-29 ##
- Lade till följande metoder i klassen Utilities:
  - `isValidUUID()`

## [1.0.0] - 2020-06-25 ##
- Lade till följande metoder i klassen Utilities:
  - `reportSubjectToString()`
- Gjorde arbete på följande klasser:
  - `ReportQuestionContent`
- pom.xml: lade till nya beroenden
- Lade till följande klasser:
  - `EmailConfiguration`
  - `EmailService`
- Det går nu att rapportera en fråga vid fel alternativt olämpligt innehåll.

## 2020-06-24 ##
- Lade till kod till följande JS funktioner:
  - `reportQuestionError()`
  - `reportQuestionContent()`
- Gjorde arbete på följande klasser:
  - `ReportQuestionError`
- Lade till följande metoder i klassen Utilities:
  - `intToReportSubject()`

## 2020-06-23 ##
- Gjorde arbete på följande mallar:
  - `askSoundQuestion.html`
  - `askImageQuestion.html`
- Lade till följande filer:
  - `ReportController.java`
  - `ReportSubject.java` (enum)
  - `ReportQuestionError.java`
  - `ReportQuestionContent.java`

## 2020-06-22 ##
- Lade till nya stilar
- Lade till följande filer:
  - `reportQuestionErrors.js`
  - `reportQuestionContents.js`
- Gjorde arbete på följande mallar:
  - `askTextQuestion.html`
- Ändrade följande fragment:
  - `modalReportQuestionErrors.html`
  - `modalReportQuestionContents.html`

## 2020-06-18 ##
- Lade till följande filer:
  - `modalReportQuestionErrors.css`
  - `modalReportQuestionContents.css`
  - `modalReportQuestionErrors.html`
  - `modalReportQuestionContents.html`

## 2020-06-17 ##
- Lade till nya stilar
- Lade till följande filer:
  - `popup.css`

## 2020-06-16 ##
- Lade till följande fragment:
  - `commonInput.html`
- På den sista frågan vid skapande av Quiz skrivs "Create Quiz" ut
  istället för "Add question".
- Lade till följande mallar:
  - `finishedQuizV2.html`

## 2020-06-15 ##
- Lade till följande metoder i klassen MainController:
  - `deleteQuestion()`
- Gjorde arbete på följande mallar:
  - `addTextQuestion.html`
  - `addSoundQuestion.html`
  - `addImageQuestion.html`
- **Backfunktionen när man skapar Quiz fungerar nu**!

## 2020-06-12 ##
- Fortsatt arbete med en backfunktion

## 2020-06-11 ##
- Lade till följande scripts:
  - `goForward.js`
- Uppdaterade `style.css`
- Arbetat med att fixa en backfunktion. (Ej klar.)

## 2020-06-10 ##
- Lade till nya stilar
- Gjorde arbete på följande mallar:
  - `finishedQuiz.html`

## [1.0.0b] - 2020-06-08 ##
- Lade till följande filer:
  - `answerQuestion.js`
  - `evaluateAnswer.js`
  - `modalOutAnswer.css`
- Lade till mapping för: `/evaluateAnswer`
- Lade till nya fragment

## 2020-06-05 ##
- Lade till följande scripts:
  - `goBack.js`
- Fixade förhandsgranskning av bild vid bildfråga (vid skapande av quiz)

## 2020-06-04 ##
- Fixade databasinställningar för testning respektive produktion
- Fixade visning av programversion i sidans huvud
- Lade till möjligheten att specificera hur många frågor ett Quiz ska innehålla
- Lade till följande mallar:
  - `abortCreateQuiz.html`
- Lade till följande mappings:
  - `/abortCreateQuiz`
- PlayController: getQuestion: förbättrade algoritm
- Fixade hantering av om en och samma fråga blir besvarad två eller fler gånger

## 2020-06-03 ##
- När man skapar Quiz går det nu att välja/byta 1) frågetyp 2) antal svarsalter-  
  nativ **på samma sida som frågan själv**.

## 2020-06-02 ##
- Uppdaterade TODO
- Satte maxlängd på
  - quiztitel (45 tecken)
  - frågor (180 tecken)
  - svarsalternativ (80 tecken)
- Gällande filtrering av Quiz: lade till valmöjligheten 'All'
- Lade till följande metoder i klassen `MainController`:
  - `getFilteredQuizzesByTopic()`
  - `getFilteredQuizzesByLang()`

## 2020-06-01 ##
- Satte upp följande gränsvärden:
  - `optTextMaxLen`

## 2020-05-31 ##
- Satte upp följande gränsvärden:
  - `quizTitleMaxLen`
  - `questionMaxLen`
- Tog bort onödiga null checks
- Tog bort följande oanvända metoder i klassen MainController:
  - `getQuizId()`
  - `getQuizUniqueId()`
  - `getQuizByUniqueId()`
- Tog bort följande oanvända metoder i klassen PlayController:
  - `getAllQuestionsForQuiz()`

## [1.0.0a] - 2020-05-29 ##
- Lade till nya stilar för knappar i `style.css`
- Ändrade utseende för knappar

## 2020-05-28 ##
- Lade till null checks för:
  - `createQuizBegin()`
- Fixade förbättrad validering av input för följande mappings:
  - `/addQuestion`
  - `/addTextQuestion`
  - `/addSoundQuestion`
  - `/addImageQuestion`
- Lade till ny klass `OptionAndText`
- Lade till följande metoder i klassen `QuestionAndAnswer`:
  - `getListOfAnswers()`
  - `getListOfRightAnswers()`
  - `getQuestionNum()`
  - `getQuestionText()`
  - `hasTwoOrMoreAnswers()`
  - `isRightAnswer()`
- Lade till nya stilar i `style.css`
- Ändrade följande mallar:
  - `finishedQuiz.html`

## 2020-05-27 ##
- Ändrade följande mallar:
  - `exitQuiz.html`
- Lade till följande klasser:
  `QuestionAndAnswer`
- Fixade skrivfel
- Lade till följande metoder i klassen `PlayController`:
  - `getAnswer()`
  - `getAllQna()`
- Gjorde arbete på följande mappings:
  - `/finishedQuiz`

## 2020-05-26 ##
- Lade till nya fragment:
  - `questionAnsAlt.html`
- Ändrade följande mallar:
  - `askTextQuestion.html`
  - `askSoundQuestion.html`
  - `askImageQuestion.html`
- Fixade mapping för:
  - `/finishedQuiz`
- Lade till ny klass: Answer
- Lade till nytt förvar: AnswerRepo
- Lade till följande mallar:
  - `exitQuiz.html`
  - `finishedQuiz.html`

## 2020-05-25 ##
- Lade till nya stilar
  - `checkbox.css`
- Lade till mapping för:
  - `/answerQuestion`
  - `/exitQuiz`
- Gjorde arbete på följande mallar:
  - `askTextQuestion.html`

## 2020-05-24 ##
- Gjorde arbete på följande mallar:
  - `askSoundQuestion.html`
  - `askImageQuestion.html`
- Fixade en bugg i metoden `getQuestion()` (PlayController)
- Fixade null checks för följande metoder:
  - `getQuizUniqueId()`
  - `getQuiz()`
  - `getQuizByUniqueId()`
  - `getQuestion()`

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
Startsidan listar nu quiz och det är möjligt att filtrera visningen
med hjälp av att ange ämne/kategori och språk.

I nuläget när man trycker på "Create new quiz" får man ange
    1. Titel
    2. Ämne/kategori
    3. Vilket språk frågorna i quizet kommer vara på

När detta är gjort finns det en knapp "Ok, start adding questions". I
samband med att trycka på knappen förs tillgänglig data in i
databasen. Tanken är sedan att användaren ska få börja lägga till
frågor och svar, men denna funktionalitet är inte skriven ännu.

## 2020-05-16 ##
- Lade till följande metoder i klassen Utilities
  - `topicToString()`
  - `languageToString()`

## 2020-05-15 ##
Arbetat med visning av startsidan som skall lista quiz.
