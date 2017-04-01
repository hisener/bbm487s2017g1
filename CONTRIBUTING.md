# Contributor's Guide

[How to Contribute to Open Source](https://opensource.guide/how-to-contribute/)

## Prerequisites
- git (2.7.4)
- Java (1.8.0_121)
- Apache Maven (3.3.9)
- MySQL (v5.7.17)
- MySQL Workbench (6.3) **(optional)**

> _version numbers in parentheses are the version that we use for development._

## Contributing

1. Clone the project repo
```shell
git clone https://github.com/hisener/bbm487s2017g1.git
```
> _Note:_ If you are not a member of the team. You should fork the project first.

2. Create a branch
```shell
git checkout -b [name_of_your_new_branch]
```
> Name the branch something like `feature/xxx` or `fix/xxx`
where `xxx` is a short description of the changes or feature.

3. Commit your changes (`git commit`). Please note that good commit messages are important.
4. Push your commits to GitHub.
```shell
git push origin [name_of_your_new_branch]
```
5. Create a Pull Request using GitHub interface.

## Running the application (from source code)

1. Clone the project repo
```shell
git clone https://github.com/hisener/bbm487s2017g1.git
```

2. Build and install artifacts
```shell
mvn clean install
```

3. Run the application
```shell
java -jar target/lbls-1.0-SNAPSHOT-jar-with-dependencies.jar # Change "1.0-SNAPSHOT" according to the version.
```

## Running the tests

```shell
mvn test
```
