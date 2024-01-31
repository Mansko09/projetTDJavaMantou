# ENSEA Game Project

# How to import this project in IntelliJ IDE :

1. In the IDE, go to `File > New > Project from Version Control`, under URL paste this repository's url (found under `<>Code > HTTPS` on github)
2. Make sure you have javafx downloaded in a easily aceessible folder
3. Go to `File > Project Structure > Libraries > Add`, and copy the path of the /lib folder of javafx
4. In the top right, click on `Main > Edit configuration > Main`, under VM options, copy this :
```
--module-path <./lib file path> --add-modules javafx.controls
```
Make sure you :
- replace `<./lib file path>` with your /lib folder's path
- replace the path for the hero and the foes frames with the path on your computer for "heros.png"

You can now run the code. 
If the IDE tells you that there is a problem with the JDK, click on solve automatically and wait for the download to end, then run the code again

# How to play the game :
1. The game starts as soon as you click on the start button.
2. To move, press the left and right keys on your keyboard. 
3. To jump press on the spacebar.
4. To shoot press on the UP key.
5. You have 4 lives (hearts), if you get shot at or touch a foe, you lose one life.
6. If you lose all your lives then it is GAME OVER.
6. Slay all the monsters and rise to different levels to win and save the world.

