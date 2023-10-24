# Project: Wheel of Fortune and Mastermind with Inheritance

[Rubric](https://docs.google.com/spreadsheets/d/1LXqTcoQ2DWKJW9METt6XJ4bg3ZLl3-gbDq6sT7MnoAw/edit#gid=0)

The purpose of this project is for you to gain experience with object-oriented design and inheritance, polymorphism, interfaces and abstract classes. In part 1, you’ll refactor your existing Wheel of Fortune code to use inheritance in various ways, and you'll code classes  “GameInstance” and  “GamesRecord” that can be used with Wheel of Fortune and other games. In part 2, you’ll design and code Mastermind and refactor Wheel of Fortune so that the similarities between the games are codified in a superclass.

## Part I. WheelOfFortune Refactor

Re-code Wheel of Fortune using inheritance, polymorphism, interfaces, and abstract classes, and as specified below. The result will be two games, one for users and one for AI players, both of which allow a set of games to be played, recorded, and reported on.

**class GameRecord**

GameRecord keeps track of the score (integer) and player id (String) for a single play of a game. It must implement Comparable and provide a default implementation of compareTo which compares scores.

**class AllGamesRecord** should have the following methods:

- add(GameRecord)-- adds a GameRecord to the AllGamesRecord
- average()-- returns the average score for all games added to the record
- average(playerId) -- returns the average score for all games of a particular player
- highGameList(n)-- returns a sorted list of the top n scores including player and score. This method should use the Collections class to sort the game instances.
- highGameList(playerId, n)-- returns a sorted list of the top n scores for the specified player.. This method should use the Collections class to sort the game instances.

**Abstract class Game**

class Game will encapsulate the code for looping through a set of games and recording the results. It should define 

- AllGamesRecord playAll()— a method that plays a set of games and records and returns an AllGamesRecord object for the set.

Class Game should also define these abstract methods:

- GameRecord play()-- plays a game and returns a GameRecord
- boolean playNext() -- asks if the next game should be played (this will be called even to check if the first game should be played). The function should return a boolean.

It will be the job of the subclasses of Game to implement these methods.

**Abstract class WheelOfFortune, extends Game.** This class will have much of the code from your existing WheelOfFortune class, including readPhrases, randomPhrase, getHiddenPhrase, and processGuess. It should also define an abstract method getGuess(String previousGuesses), which returns a char, thus requiring all concrete WheelOfFortune games to implement it. And of course WheelOfFortune needs to implement the abstract methods in its parent Game.

**WheelOfFortuneUserGame**, **a concrete extension of WheelOfFortune**. This class should override the “getGuess” method of the abstract WheelOfFortune class, using Scanner to get the guess.

WheelOfFortuneUserGame should allow the user to play each game with a random phrase, and if there are more phrases, ask after the game if the player wants to continue. Once a particular phrase is used, it should be discarded from the phrase list so it isn’t chosen again. The class should implement the play() and playNext() methods from Game in order to make these things happen.

For both WheelOfFortunedUserGame and WheelOfFortuneAIGame, the main function should just call playAll() to play a set of games, like in the code below:

```java
public static void main(String [] args) {
   HangmanUserGame hangmanUserGame = new HangmanUserGame();
   AllGamesRecord record = hangmanUserGame.playAll();
   System.out.println(record);  // or call specific functions of record
}
```

**WheelOfFortuneAIGame, another concrete extension of WheelOfFortune.** For each AI player specified, this class will play a game for each phrase read in from the file. So if there are m players and n phrases, Game’s playAll() should play m*n games.

WheelOfFortuneAIGame should have three constructors. One should set the WheelOfFortunePlayer to a default player, one should allow the client to specify a single concrete WheelOfFortunePlayer, and one should accept a list of WheellOfFortunePlayers.

As with WheelOfFortuneUserGame, WheelOfFortunedAIGame’s main should call Game’s playAll() to play the set of games, and play() and playNext() should be defined to make this happen.

**WheelOfFortunePlayer Interface**

This interface should define the folllowing methods needed by all WheelOfFortunePlayers:

- char nextGuess()  — get the next guess from the player
- String playerId()  — an id for the player
- void reset() — reset the player to start a new game

**Define** **At least 3 Concrete implementations of WheelOfFortunePlayer**

Code three implementations of the WheelOfFortunePlayer interface.

**“Main” programs**

WheelOfFortuneUserGame and WheelOfFortunedAIGame should have main programs that run a set of games. Both should call Game’s playAll() to do this. WheelOfFortunedUserGame should allow the user to play until they quit or run out of phrases. WheelOfFortunedAIGame should create at least three different players, then call playAll() to run through all the phrases for each player. Both main’s should demonstrate the methods of GamesRecord to display results.

**DESIGN AND COMMUNICATE**

This project is about coding, but also about design and communication. It is your job to read the above specification and communicate with your client (Prof. Wolber) until you understand it and can code the program. This project is not just about getting a program to work: the code must be designed as specified for full credit.

## Part 2. Mastermind and WheelOfFortune are Similar Games

Mastermind and WheelOfFortune are two games with similarities. The task for part 2 is to design and code a user-based Mastermind game and re-factor WheelOfFortune so that as much of the code as possible for the two games is placed in a superclass named “GuessingGame”.

Create a new project and repo for part 2 and begin with your code from part 1.

The goal of part 2 is to encapsulate code within GuessingGame so that it can be reused in WheelOfFortuned and Mastermind-- communicate with your client before coding!

**Mastermind**

[Mastermind specification](https://usfca.instructure.com/courses/1586841/pages/mastermind-specification?module_item_id=16842979)

[Some important code you can use](https://usfca.instructure.com/courses/1586841/pages/mastermind-sample-code?module_item_id=16898183)

Note: You may propose a different “other” game than Mastermind, as long as there are similarities with WheelOfFortune7 (the project is about inheritance and code reuse)
