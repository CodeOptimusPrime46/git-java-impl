# git-java-impl

`git-java-impl` is a Java-based implementation of a Git-like version control system. It provides basic functionalities such as repository initialization, adding files to the
staging area, committing changes, viewing commit logs, and checking the repository status. The project is built using Java and Maven.

## Features

- **Repository Initialization**: Create a `.vcs` directory structure for version control.
- **Add Files**: Add files to the staging area.
- **Commit Changes**: Commit changes with a message.
- **View Logs**: Display the commit history.
- **Check Status**: Show the current status of the repository.

## Prerequisites

- Java 17 or higher
- I used Maven 3.9 so use your wisdom :)

## Building the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/CodeOptimusPrime46/git-java-impl.git
2. Navigate to the project directory:
   ```bash
   cd git-java-impl
   ```
3. Build the project using Maven:
   ```bash
    mvn clean install
    ```
4. Run the application:
   ```bash
   java -jar target/git-java-impl-1.0-SNAPSHOT.jar
   ```

## Usage
1. Initialize a new repository:
   ```bash
   java -jar target/git-java-impl-1.0-SNAPSHOT.jar init
   ```
   
2. Add files to the repository:
   ```bash
   java -jar target/git-java-impl-1.0-SNAPSHOT.jar add <filename>.<extension>
   ```
3. Commit changes with a message:
   ```bash
    java -jar target/git-java-impl-1.0-SNAPSHOT.jar commit -m "Your commit message"
    ```
4. View the commit log:
    ```bash
    java -jar target/git-java-impl-1.0-SNAPSHOT.jar log
    ```
5. Check the status of the repository:
    ```bash
    java -jar target/git-java-impl-1.0-SNAPSHOT.jar status
    ```
6. View the contents of a index:
    ```bash
    java -jar target/git-java-impl-1.0-SNAPSHOT.jar show-index
    ```
## Pending Features

- **diff**: Implement functionality to show differences between the working directory and the last commit.
- **File Deletion**: Implement functionality to delete files from the repository.
- **Branching**: Implement support for creating and switching branches.
- **pull**: Implement functionality to pull changes from a remote repository.
- **push**: Implement functionality to push changes to a remote repository.
- **Merging**: Implement functionality to merge branches.
- **Conflict Resolution**: Implement a mechanism to handle merge conflicts.
- **Tagging**: Implement functionality to tag specific commits.

