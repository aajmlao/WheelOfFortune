# wheel-of-fortune-4-versions-aajmlao
wheel-of-fortune-4-versions-aajmlao created by GitHub Classroom
## **Functional requirements**

- The phrases for the game should be read in from a file, the one included in the starter code you’ll be given. The game chooses a random phrase from the list. This is the phrase the player tries to guess.
- The game generates a hidden phrase in which all letters in the phrase are replaced with asterisks. Spaces and punctuation-- all non-letters-- are left unhidden. So if the phrase is "Joe + Programmer", the initial partially hidden phrase is: *** + **********
- The user guesses a letter. All occurrences of the letter in the phrase are replaced in the partially hidden phrase. For our example, if the user guessed "o", the new partially hidden phrase would change to: * o * + ** o*******. Note that for this project, vowels and consonants are treated the same (unlike the TV version in which you “buy” vowels).
- If a user guesses something that is not a letter (e.g., ‘%’) the user should be notified but not penalized a miss. Case should be ignored in terms of matching-- 'a' is the same as 'A'.
- If the guessed letter does not occur in the phrase, the user is notified of the miss and how many misses/chances are left.
- The user should NOT be penalized for guessing a previously correct or incorrect guess, but should be notified.
- If the user misses n times, the game is lost and the user should be notified. If all letters in the phrase are guessed, the user wins! You can set the number of guesses 'n' how you want.
- Provide a reasonable command-line user interface such that the players is given instructions on how to play, the current hidden code, number of misses and previous misses are displayed after each guess, and it is clear how to proceed at each step. Test this out by performing a usability test with another human or humans!
