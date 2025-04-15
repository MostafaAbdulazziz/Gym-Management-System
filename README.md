# Gym Management System

![Java](https://img.shields.io/badge/Java-17-orange) ![Swing](https://img.shields.io/badge/Swing-GUI-blue) ![License](https://img.shields.io/badge/License-MIT-green)

The Gym Management System is a desktop application developed as part of the CC272 Programming II course at Alexandria University. Built using Java and Swing, it provides an intuitive interface for gym administrators and trainers to manage gym operations efficiently. The system supports managing trainers, members, classes, and class registrations, with data persistence using text files. It incorporates input validations, a structured architecture, and a user-friendly GUI to ensure a seamless experience for users.

## Screenshots

### Gym Management System Welcome Screen
![Welcome Screen](src/resoucres/main.png)  
*The initial screen allowing users to choose between Admin and Trainer roles.*

### Admin Login Page
![Admin Login Page](images/admin_login_page.png)  
*The login page for administrators to access the system.*

### Trainer Login Page
![Trainer Login Page](images/trainer_login_page.png)  
*The login page for trainers to access their role-specific features.*

### Admin Role Menu
![Admin Role Menu](images/admin_role_menu.png)  
*The main menu for admins, providing options to add, view, remove trainers, and logout.*

### Add Trainer Page
![Add Trainer Page](images/add_trainer_page.png)  
*The page for admins to add a new trainer with details like ID, name, email, specialty, and phone number.*

### View Trainers Page
![View Trainers Page](images/view_trainers_page.png)  
*The page displaying a list of all trainers in the system.*

### Trainer Role Menu
![Trainer Role Menu](images/trainer_role_menu.png)  
*The main menu for trainers, offering options to manage members, classes, and registrations.*

### Add Class Page
![Add Class Page](images/add_class_page.png)  
*The page for trainers to add a new class with details like ID, name, trainer, duration, and max participants.*

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Architecture and Design](#architecture-and-design)
- [Algorithms and Techniques](#algorithms-and-techniques)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## Features

### Admin Role
- **Add Trainer**: Admins can add new trainers with details such as ID, name, email, specialty (e.g., Yoga, Strength Training), and phone number. The system prevents duplicate trainer IDs.
- **View Trainers**: Displays a list of all trainers in the system, showing their ID, name, email, specialty, and phone number.
- **Remove Trainer**: Admins can remove trainers by their ID, ensuring the trainer list is up to date.
- **Logout**: Saves all trainer data to `Trainers.txt` before logging out, ensuring data persistence.

### Trainer Role
- **Add Member**: Trainers can add new members with details like ID, name, membership type (e.g., Monthly, Yearly), email, phone number, and status (e.g., active, expired). Duplicate member IDs are prevented.
- **View Members**: Displays a list of all members in the system.
- **Add Class**: Trainers can create new classes by specifying class ID, name, trainer ID, duration, and maximum participants. Duplicate class IDs are not allowed.
- **View Classes**: Shows a list of all classes available in the system.
- **Register Member for Class**: Allows trainers to register members for classes, checking for available seats and updating the class's available seats upon successful registration.
- **Cancel Registration**: Trainers can cancel a member’s class registration, increasing available seats if the cancellation is within 3 days of registration.
- **View Registrations**: Displays all class registrations, including member ID, class ID, registration date, and status.
- **Logout**: Saves all member, class, and registration data to their respective files (`Members.txt`, `Class.txt`, `Registration.txt`) before logging out.

### General Features
- **Input Validation**: All user inputs are validated to ensure correctness (e.g., email format, phone number format, non-duplicate IDs).
- **Data Persistence**: Stores data in text files (`Trainers.txt`, `Members.txt`, `Class.txt`, `Registration.txt`) in a comma-separated format for easy access and modification.
- **User-Friendly Interface**: Built with Java Swing, providing an intuitive GUI with background images reflecting the gym theme.

## Technologies Used

- **Java**: Version 17
- **Swing**: For building the graphical user interface
- **Text Files**: For data storage (`Trainers.txt`, `Members.txt`, `Class.txt`, `Registration.txt`)
- **Git**: For version control

## Architecture and Design

- **Layered Architecture**:
  - **Model Layer**: Includes data classes like `Trainer`, `Member`, `Class`, and `MemberClassRegistration` to represent entities, and database classes like `TrainerDatabase`, `MemberDatabase`, `ClassDatabase`, and `MemberClassRegistrationDatabase` to handle file I/O and data manipulation.
  - **View Layer**: Swing-based GUI components for user interaction (e.g., login pages, role menus, forms for adding trainers/classes).
  - **Controller Layer**: Role classes (`AdminRole`, `TrainerRole`) manage business logic and coordinate between the model and view layers.
- **Object-Oriented Principles**:
  - **Encapsulation**: Classes like `Trainer` and `Member` have private fields with access controlled via methods (e.g., `lineRepresentation`, `getSearchKey`).
  - **Single Responsibility**: Each class has a specific role (e.g., `TrainerDatabase` handles trainer data, `AdminRole` manages admin operations).
- **Design Patterns**:
  - **Repository Pattern**: Database classes (`TrainerDatabase`, `MemberDatabase`, etc.) act as repositories for managing data access and persistence.
  - **Facade Pattern**: Role classes (`AdminRole`, `TrainerRole`) provide a simplified interface to interact with multiple database classes.

## Algorithms and Techniques

- **Search Functionality**:
  - **Algorithm**: Linear search is used in database classes (e.g., `contains`, `getRecord`) to find records by their search keys (e.g., trainer ID, member ID).
  - **Complexity**: O(n) time complexity, where n is the number of records in the list.
- **Input Validation**:
  - **Technique**: Rule-based validation using regular expressions and custom checks.
  - **Examples**:
    - Email validation ensures the format matches a pattern (e.g., `user@domain.com`).
    - Phone numbers are checked for numeric format and length.
    - Duplicate IDs are prevented by checking against existing records.
- **File I/O**:
  - **Technique**: Reading and writing to text files in a comma-separated format.
  - **Process**:
    - `readFromFile`: Reads each line, parses it into an object, and stores it in an ArrayList.
    - `saveToFile`: Writes all records back to the file, overwriting the previous content.
  - **Complexity**: O(n) for both reading and writing, where n is the number of records.
- **Class Registration Management**:
  - **Technique**: Seat availability is checked before registration, and seats are updated upon registration or cancellation.
  - **Cancellation Policy**: Uses date comparison to check if cancellation is within 3 days of registration, leveraging `LocalDate` for date arithmetic.

## Project Structure

```
Gym-Management-System/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── gymmanagementsystem/
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── Trainer.java
│   │   │   │   │   │   ├── Member.java
│   │   │   │   │   │   ├── Class.java
│   │   │   │   │   │   ├── MemberClassRegistration.java
│   │   │   │   │   ├── database/
│   │   │   │   │   │   ├── TrainerDatabase.java
│   │   │   │   │   │   ├── MemberDatabase.java
│   │   │   │   │   │   ├── ClassDatabase.java
│   │   │   │   │   │   ├── MemberClassRegistrationDatabase.java
│   │   │   │   │   ├── role/
│   │   │   │   │   │   ├── AdminRole.java
│   │   │   │   │   │   ├── TrainerRole.java
│   │   │   │   │   ├── view/
│   │   │   │   │   │   ├── WelcomeView.java
│   │   │   │   │   │   ├── AdminLoginView.java
│   │   │   │   │   │   ├── TrainerLoginView.java
│   │   │   │   │   │   ├── AdminRoleView.java
│   │   │   │   │   │   ├── AddTrainerView.java
│   │   │   │   │   │   ├── ViewTrainersView.java
│   │   │   │   │   │   ├── TrainerRoleView.java
│   │   │   │   │   │   ├── AddClassView.java
│   │   │   │   │   ├── Main.java
│   │   ├── resources/
│   │   │   ├── images/
│   │   │   │   ├── welcome_screen.png
│   │   │   │   ├── admin_login_page.png
│   │   │   │   ├── trainer_login_page.png
│   │   │   │   ├── admin_role_menu.png
│   │   │   │   ├── add_trainer_page.png
│   │   │   │   ├── view_trainers_page.png
│   │   │   │   ├── trainer_role_menu.png
│   │   │   │   ├── add_class_page.png
│   │   │   ├── data/
│   │   │   │   ├── Trainers.txt
│   │   │   │   ├── Members.txt
│   │   │   │   ├── Class.txt
│   │   │   │   ├── Registration.txt
├── README.md
├── LICENSE
├── .gitignore
```

- `model/`: Defines data entities like `Trainer`, `Member`, `Class`, and `MemberClassRegistration`.
- `database/`: Manages file I/O and data operations for each entity.
- `role/`: Implements business logic for Admin and Trainer roles.
- `view/`: Swing-based GUI components for the frontend.
- `resources/data/`: Stores text files for data persistence.
- `resources/images/`: Contains screenshots for the `README.md`.

## Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code with Java extensions (optional but recommended)
- **Git**: To clone the repository

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/MostafaAbdulazziz/Gym-Management-System.git
   cd Gym-Management-System
   ```

2. **Verify Java Version**:
   ```bash
   java -version
   ```
   Ensure the output shows Java 17 or higher.

3. **Set Up the Data Files**:
   - Ensure the `resources/data/` directory contains the following text files: `Trainers.txt`, `Members.txt`, `Class.txt`, and `Registration.txt`.
   - If not present, create empty files with the following initial content:
     ```
     // Trainers.txt (empty example)
     T002,sdfe,ergre@gmail.com,Yoga,12341234
     T123,nourrrr,hammo@sg.vc,Yoga,1234567899

     // Members.txt
     (empty)

     // Class.txt
     (empty)

     // Registration.txt
     (empty)
     ```

4. **Add the Screenshots**:
   - Ensure the `resources/images/` directory contains the screenshots with the following filenames:
     - `welcome_screen.png`
     - `admin_login_page.png`
     - `trainer_login_page.png`
     - `admin_role_menu.png`
     - `add_trainer_page.png`
     - `view_trainers_page.png`
     - `trainer_role_menu.png`
     - `add_class_page.png`

## Running the Application

1. **Compile and Run**:
   - If using an IDE, open the project and run `Main.java`.
   - Alternatively, compile and run from the terminal:
     ```bash
     javac -d . src/main/java/com/gymmanagementsystem/*.java src/main/java/com/gymmanagementsystem/**/*.java
     java com.gymmanagementsystem.Main
     ```

2. **Access the Application**:
   - The application launches with a welcome screen where you can choose between Admin and Trainer roles.
   - For **Admin Login**, use default credentials (e.g., username: `admin`, password: `admin123`) or set up your own in the code.
   - For **Trainer Login**, use default credentials (e.g., username: `trainer`, password: `trainer123`) or set up your own.
   - Navigate through the role-specific menus to manage trainers, members, classes, and registrations.

## Contributing

We welcome contributions to enhance the Gym Management System! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes and commit (`git commit -m "Add your feature"`).
4. Push to your branch (`git push origin feature/your-feature`).
5. Open a pull request with a clear description of your changes.

Please follow the [Code of Conduct](CODE_OF_CONDUCT.md) and ensure tests (if any) pass.

## License

This project is licensed under the [MIT License](LICENSE). See the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Developed by Mostafa Abdul Aziz as part of the CC272 Programming II course at Alexandria University.
- Supervised by Dr. Layla Abou-Hadeed and teaching assistants.
- Thanks to the open-source community for tools and libraries.

---

### Step 3: Steps to Update the GitHub Repository

To integrate this `README.md` and the screenshots into your GitHub repository, follow these steps:

1. **Add the Screenshots**:
   - Create a `resources/images/` directory in your repository if it doesn’t exist.
   - Save the provided screenshots with the following filenames:
     - `welcome_screen.png` (Gym Management System welcome screen)
     - `admin_login_page.png` (Admin Login)
     - `trainer_login_page.png` (Trainer Login)
     - `admin_role_menu.png` (Admin Role menu)
     - `add_trainer_page.png` (Add Trainer page)
     - `view_trainers_page.png` (View Trainers page)
     - `trainer_role_menu.png` (Trainer Role menu)
     - `add_class_page.png` (Add Class page)

2. **Update the `README.md`**:
   - Replace the existing `README.md` (if any) with the one provided above.
   - Ensure the image paths in the `README.md` match the actual locations (`resources/images/`).

3. **Add a `LICENSE` File (if not already present)**:
   - Create a file named `LICENSE` in the root directory with the MIT License text:
     ```
     MIT License

     Copyright (c) 2025 Mostafa Abdul Aziz

     Permission is hereby granted, free of charge, to any person obtaining a copy
     of this software and associated documentation files (the "Software"), to deal
     in the Software without restriction, including without limitation the rights
     to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
     copies of the Software, and to permit persons to whom the Software is
     furnished to do so, subject to the following conditions:

     The above copyright notice and this permission notice shall be included in all
     copies or substantial portions of the Software.

     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
     SOFTWARE.
     ```

4. **Commit and Push**:
   - Stage the files:
     ```bash
     git add README.md resources/images/welcome_screen.png resources/images/admin_login_page.png resources/images/trainer_login_page.png resources/images/admin_role_menu.png resources/images/add_trainer_page.png resources/images/view_trainers_page.png resources/images/trainer_role_menu.png resources/images/add_class_page.png LICENSE
     ```
   - Commit the changes:
     ```bash
     git commit -m "Add README with features, algorithms, and screenshots"
     ```
   - Push to GitHub:
     ```bash
     git push origin main
     ```

### Notes and Assumptions
- **Project Structure**: I’ve assumed a typical Java project structure with packages for `model`, `database`, `role`, and `view`. If your actual structure differs, update the `Project Structure` section in the `README.md` accordingly.
- **Default Credentials**: The `README.md` mentions default credentials for login (`admin/admin123` for Admin, `trainer/trainer123` for Trainer). Since the lab document doesn’t specify how authentication is implemented, I’ve assumed you’ve added a simple username/password check in the code. If this isn’t the case, you can remove or modify this part.
- **Input Validation**: You mentioned implementing validations for all user inputs. I’ve included this in the features and algorithms sections, assuming validations like email format checks and duplicate ID prevention. If you have specific validation rules, you can add them to the `Algorithms and Techniques` section.
- **UML Diagrams**: The lab requires UML diagrams, but since you didn’t provide them and the task is to create a `README.md`, I’ve excluded them. If you’d like to include references to UML diagrams, you can add a section like “UML Diagrams” and link to them (e.g., if they’re in a `docs/` directory).
- **Screenshots**: The screenshots are referenced in the `README.md` with the paths `resources/images/`. If you prefer a different directory (e.g., `images/` at the root), update the paths in the `README.md` and the installation steps accordingly.

This `README.md` provides a professional overview of your Gym Management System, suitable for a large GitHub project, and includes all the required elements you specified. Let me know if you’d like to make any adjustments!
