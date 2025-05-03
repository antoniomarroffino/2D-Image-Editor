# 🖼️ 2D Image Editor - Standalone Software

## 🧩 Description

This project is a complete 2D image editor built with **JavaFX** as a standalone desktop application, designed to work with **PNM image formats** (PBM, PGM, PPM).  
It was developed as part of the **Software Engineering II** and **Operating Systems** modules at SUPSI (2024–2025).

The editor supports a modular and extensible design and follows Agile principles (Scrum), unit and UI testing, and strong architectural practices (Layering, MVC, Design Patterns).

## 🧱 Software Architecture

The application is composed of two clearly separated Maven modules:

### 🔧 Backend

Implements a **Layered Architecture**:

- **Application Layer**: coordination and orchestration
- **Business Layer**: transformation logic, error management
- **Data Access Layer**: reading/writing PNM files

The backend is **fully decoupled** from the GUI and is designed for reusability and testability.

### 🎨 Frontend

Developed in **JavaFX** using **MVC**, with a dedicated controller dispatching events and updates to the view.  
Notable design features:

- **Observer Pattern**: notifies UI components of model changes
- **Adapter Pattern**: unifies interaction between different UI elements (e.g., buttons, menu items)
- **Abstract Factory Pattern**: dynamic creation of format-specific image readers

## 🎮 Main Features

- 📂 Load and display **PNM images** (PBM, PGM, PPM – plain text and binary)
- 🔄 Apply image transformations:
  - Flip (vertical and horizontal)
  - Rotate 90° (clockwise and counter-clockwise)
  - Negative
- 🧪 Prepare and run a **pipeline** of transformation steps
- 💾 Save modified images in PNM format
- 🕓 Reopen recently used images
- 🌐 Multilingual UI without real-time switching
- 📁 User preferences saved in a plain-text config file
- ℹ️ Application info panel (version, build date, credits)

## 🛠️ Technologies Used

- **Language:** Java 17
- **GUI Framework:** JavaFX
- **Build Tool:** Maven (multi-module)
- **Patterns:** MVC, Observer, Adapter, Abstract Factory
- **Persistence:** PNM format, preferences in plain text
- **Testing:** JUnit 5, Mockito, JavaFX UI testing (robot)

## 🚀 How to Run the Project

> ⚠️ Ensure Java (JDK 17 or higher) is installed.

1. Clone the repository:
   ```bash
   git clone https://github.com/antoniomarroffino/2D-Image-Editor.git
   ```
2. Navigate into the project folder:
   ```bash
   cd 2D-Image-Editor
   ```
3. Build and run the application:
   ```bash
   mvn javafx:run
   ```

Alternatively, execute the standalone `.jar` file located in the `build/` or `target/` directory.

## 📦 Requirements

- Java Development Kit (JDK) 17+
- JavaFX SDK (bundled or added via Maven)
- OS Compatibility: Windows, macOS, Linux

## 📚 Educational Goals

During the development, the following software engineering practices were applied:

- Agile methodology with Scrum (sprints, backlog, iterations)
- Software architecture principles (layering, modularization)
- Design patterns to support maintainability and scalability
- Internationalization and user interaction design
- Configuration management (Git, Maven)
- Comprehensive testing (unit + end-to-end)

The system aligns with the **Open-Closed Principle (OCP)**, allowing new editing operations or file formats to be added without modifying the existing code.

## ✅ Project Status

✔️ Completed in December 2024  
🔒 No further maintenance planned

## 👤 Author

**Antonio Marroffino**
- GitHub: [github.com/antoniomarroffino](https://github.com/antoniomarroffino)
- LinkedIn: [linkedin.com/in/antonio-marroffino](https://www.linkedin.com/in/antoniomarroffino)

---

## 📜 License

This project was developed for educational purposes as part of the Bachelor's degree in Computer Engineering at SUPSI.
