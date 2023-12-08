# ENSEA Game Project

# How to import this project in IntelliJ IDE :

1. In the IDE, go to `File > New > Project from Version Control`, under URL paste this repo's url (found under `<>Code > HTTPS` on github)
2. Make sure you have javafx downloaded in a easily aceessible folder
3. Go to `File > Project Structure > Libraries > Add`, and copy the path of the /lib folder of javafx
4. In the top right, click on `Main > Edit configuration > Main`, under VM options, copy this :
```
--module-path <./lib file path> --add-modules javafx.controls
```
Make sure you replace `<./lib file path>` with your /lib folder's path

You can now run the code. 
If the IDE tells you that there is a problem with the JDK, click on solve automatically and wait for the download to end, then run the code again
