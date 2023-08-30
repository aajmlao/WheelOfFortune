# WheelOfFortune
For this assignment, you will create 4 command-line (terminal i/o) versions of the game *Wheel of Fortune.* You should work alone on the project, without human or AI assistance,  start with the provided starter code only, and discuss only conceptually with others— do not share your code or view that of others. Your goal is to learn the basic fundamentals of Java code.

## **Functional requirements**

- The phrases for the game should be read in from a file, the one included in the starter code you’ll be given. The game chooses a random phrase from the list. This is the phrase the player tries to guess.
- The game generates a hidden phrase in which all letters in the phrase are replaced with asterisks. Spaces and punctuation-- all non-letters-- are left unhidden. So if the phrase is "Joe + Programmer", the initial partially hidden phrase is: *** + **********
- The user guesses a letter. All occurrences of the letter in the phrase are replaced in the partially hidden phrase. For our example, if the user guessed "o", the new partially hidden phrase would change to: * o * + ** o*******. Note that for this project, vowels and consonants are treated the same (unlike the TV version in which you “buy” vowels).
- If a user guesses something that is not a letter (e.g., ‘%’) the user should be notified but not penalized a miss. Case should be ignored in terms of matching-- 'a' is the same as 'A'.
- If the guessed letter does not occur in the phrase, the user is notified of the miss and how many misses/chances are left.
- The user should NOT be penalized for guessing a previously correct or incorrect guess, but should be notified.
- If the user misses n times, the game is lost and the user should be notified. If all letters in the phrase are guessed, the user wins! You can set the number of guesses 'n' how you want.
- - Provide a reasonable command-line user interface such that the players is given instructions on how to play, the current hidden code, number of misses and previous misses are displayed after each guess, and it is clear how to proceed at each step. Test this out by performing a usability test with another human or humans!

## **Style Requirements**

- Style your code using the [Google Java Style Guidelines](https://google.github.io/styleguide/javaguide.html). For this first project, be sure and follow the [formattingLinks](https://google.github.io/styleguide/javaguide.html#s4-formatting) and [naming](https://google.github.io/styleguide/javaguide.html#s5-naming) guides.

## **Coding Specifications (all versions)**

- Each time the program runs, choose a random phrase.
- Define the hidden phrase as a StringBuilder, because you will be changing it as the game is played.

## **Versions**

You should create four versions of your solution— your Git project should end up with four classes. Complete each version before moving on to the next one.

V**ersion 1, WheelOfFortuneMain**: Write the program **within a single “main” method**. Do not define other methods other than main. Name the class HangmanMain. 

V**ersion 2, WheelOfFortuneMethods**: In this version, provide the following **static** methods, but **don't define any data members**:

- randomPhrase -- returns a single phrase randomly chosen from a list
- - generateHiddenPhrase -- returns the initial hidden phrase
- getGuess-- gets input from user and returns it.
- processGuess -- returns whether a letter matches, and modifies the partially hidden phrase if there is a match.

Please use these exact method names along with appropriate parameters.

**version 3, WheelOfFortuneObject**: provide the same methods as in version 2, but define them as instance methods. Define data members within the class for "phrase", "hiddenPhrase", and "previousGuesses", and use those in your code. Because of the data members, you will have fewer parameters in your methods for this version.

**version 4: WheelOfFortuneBot**

Modify your version 3 so that the game plays the same but the player is a bot.

- There is no user input-- when you run the app, it runs through the game showing the bot's guesses and the system's feedback.
- You can  points by just creating a not that intelligent bot player, e.g., one that guesses 'a', then 'b', then 'c', and so on, until they win or lose. Implement the simple player first, then strategize and make it better for full or even extra credit.
- ## **Getting Started**

1. Create your initial GitHub repo, with starter code, by clicking [this link](https://classroom.github.com/a/AByFYhpV):

2. Open the starter code project in Intellij by selecting VCS | Get from version control | GitHub and choosing your repository

3. The starter code reads in the phrases from a file and into a Java List, then selects a random string from the list. 

## **Submission**

1. (100) **Code Checkin**. Check your modified code into the GitHub project prior to the deadline. You can check your code in by selecting VCS | Commit, then choosing to commit and push. Check the GitHub repo to confirm your changes are committed. Check that you’ve completed all of the requirements in the following rubric:
    
    [**Rubric**](https://docs.google.com/spreadsheets/d/18lMVwet1XvrVoKyci_sHpzNx99NJlaSyJV6cXDOKpIY/edit?usp=sharing)
    
2. (20) **Project Testing and Documentation.** Create a project page on your Notion-based portfolio with the following information:
    - Project Title
    - Overview of project (a paragraph)
    - Link to your GitHub repository
    - - Development process (a few paragraphs). Explain your process. Discuss bugs you ran into, and explain how you solved the problems. Discuss the key Java concepts you learned and include code snippets in your explanations. Explain your algorithm for checking for matches and replacing asterisks for the matching characters. How might you have coded it differently?
- Test plan: For versions 3 and 4, describe the tests your ran to ensure your app works as specified
- Test video: Shows you running the tests and showing/explaining that everything works.(for version 3 and 4)
- a title and snapshot for the project should appear on your portfolio page

In creating your documentation page, think of it as part of your augmented-brain, a documentation of your learning, something which you’ll  refer back to later, or that someone else might learn from. Over the semester, you’ll add to this second brain.

3. (0-10) **Extra Credit.** Propose and implement additional features/rules for the game. For instance, you might implement the “buy a vowel” way to play, or allow the user to guess the entire puzzle in one swoop. Be creative!
4. (0-10) **Extra Credit.** Implement a [Mastermind game](https://www.notion.so/b3ef1a868cfe4b6f82cc2217303c9dc5?pvs=21) including an intelligent bot player.
