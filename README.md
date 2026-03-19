# 🎮 Tic-Tac-Toe Game (MVC Architecture)

## 📌 Overview

This project is a **Tic-Tac-Toe game** implemented in Java using the **Model-View-Controller (MVC)** design pattern.
It supports both:

* 🖥️ **Console-based gameplay**
* 🪟 **GUI-based gameplay**

Both interfaces share the same core game logic (`GameBoard`), ensuring **code reusability** and **clean separation of concerns**.

---

## 🏗️ Architecture

The project follows the **MVC pattern**:

### 🔹 Model (`GameBoard`)

* Handles all game logic:

    * Board state
    * Move validation
    * Win/draw detection
    * AI move generation (for computer mode)

### 🔹 View (`GameView`)

* Responsible for displaying the game (GUI version)
* Interacts with the user visually

### 🔹 Controller (`GameController`)

* Connects the Model and View
* Handles user input and updates the game state

---

## 🚀 Features

* 🎯 Two game modes:
    * Player vs Player
    * Player vs Computer (AI)
* 🧠 Basic AI for computer moves
* 🔄 Replay option after game ends
* ⚠️ Input validation
* ♻️ Reusable game logic across GUI and console versions

---

## 🖥️ Console Version

### Entry Point:

`ConsoleGameRunner.java`

### Features:

* Text-based interface
* Player name input
* Turn-based gameplay
* Input validation for moves
* Displays board after each move

---

## 🪟 GUI Version

### Entry Point:

`GUIGameRunner.java`

### Features:

* Graphical interface using MVC pattern
* Event-driven interaction
* Clean separation of UI and logic

---

## 📂 Project Structure

```
com.tictactoe
│
├── model
│   └── GameBoard.java        # Game logic (Model)
├── view
│   └── GameView.java         # GUI View
├── controller
│   └── GameController.java   # Controller
├── ConsoleGameRunner.java    # Console-based game runner
└── GUIGameRunner.java        # GUI launcher
```

---

## ▶️ How to Run

### Clone the repository.

### Build with Maven:
```bash
mvn clean install
```

### ▶️ Run Console Version

```bash
 java -cp target/tictactoe-1.0.jar com.tictactoe.ConsoleGameRunner
```

### ▶️ Run GUI Version

```bash
 java -cp target/tictactoe-1.0.jar com.tictactoe.GUIGameRunner
```

---

## 🎮 How to Play

1. Choose game mode:

    * `1` → Two Players
    * `2` → Play with Computer

2. Enter player names (if applicable)

3. Take turns entering:

    * Row (0–2)
    * Column (0–2)

4. First player to align 3 marks wins!

---

## 🧑‍💻 Author

Developed as part of a Java project demonstrating:

* MVC architecture
* Code reuse across interfaces
* Game logic design

---

## 📜 License

This project is open-source and available for learning purposes.

---
