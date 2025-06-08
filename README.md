# git-java-impl

The idea behind building this project was to **break the fourth wall and get to the core of the first tool I learned when
I started my career in 2020: Git**. I wanted to understand how it works under the hood, so I decided to build a simple
version control system in Java that mimics some of Git's core functionalities.

`git-java-impl` is a Java-based implementation of a Git-like version control system. It provides basic functionalities
such as repository initialization, adding files to the staging area, committing changes, viewing commit logs, and checking
the repository status. The project is built using Java and Maven.

In the current implementation, I'm not using any compression to deflate files and save it, file contents are saved as
is in blob for ease. Objects folder in actual git has subfolders with the first two characters of SHA-1 hash,
here I'm saving the files directly in the objects folder. The tree object and commit object are saved as a JSON file for
simplicity.

## Cool Insights I Learned!

While building this project, I learned some interesting facts about how Git works:

- **Git doesn't save changes**: Instead of saving diffs, Git saves the complete file as a blob in its object database.
- **Efficient storage**: Even though Git saves complete files, it uses compression and deduplication to optimize storage.
- **Content-addressable storage**: Git identifies objects (like blobs, trees, and commits) using SHA-1 hashes, ensuring data integrity.

These insights helped me better understand Git's internal mechanisms and inspired the design of this project.

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
   java -jar target/git-java-impl-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

## Usage

1. Initialize a new repository:
   ```bash
   java -jar target/git-java-impl-1.0-SNAPSHOT-jar-with-dependencies.jar init
   ```

2. Add files to the repository:
   ```bash
   java -jar target/git-java-impl-1.0-SNAPSHOT-jar-with-dependencies.jar add <filename>.<extension>
   ```
3. Commit changes with a message:
   ```bash
    java -jar target/git-java-impl-1.0-SNAPSHOT-jar-with-dependencies.jar commit -m "Your commit message"
    ```
4. View the commit log:
    ```bash
    java -jar target/git-java-impl-1.0-SNAPSHOT-jar-with-dependencies.jar log
    ```
5. Check the status of the repository:
    ```bash
    java -jar target/git-java-impl-1.0-SNAPSHOT-jar-with-dependencies.jar status
    ```
6. View the contents of a index:
    ```bash
    java -jar target/git-java-impl-1.0-SNAPSHOT-jar-with-dependencies.jar show-index
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

