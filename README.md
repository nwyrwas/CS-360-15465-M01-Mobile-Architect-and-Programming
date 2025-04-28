# CS-360-15465-M01-Mobile-Architect-and-Programming

# Mobile App Development Portfolio Artifact - Project Three

## Summary

This project involves the development of a mobile app that allows users to manage personal data. The app addresses the need for easy-to-use, functional management of specific categories such as inventory items, event details, or daily weight. It allows users to log in, manage data in an SQLite database, and receive SMS notifications based on the user’s preferences and actions. The app is designed to be intuitive and user-friendly, ensuring that it meets the needs of a diverse range of users while maintaining functionality for both beginners and experienced users.

## User-Centered Design

The app includes several key features and screens to address the needs of the users:
1. **Login Screen**: Users can log in with a username and password or create a new account if they don’t already have one. This is essential for personalizing the app experience.
2. **Database Grid Screen**: Displays all the stored data in a grid format, allowing users to view, add, update, and delete entries. This allows for easy interaction with the database.
3. **Notification Permissions Screen**: Prompts users to grant SMS permissions, ensuring they can receive alerts for things like low inventory or upcoming events.

In my design process, I focused on creating a clean and simple user interface, ensuring that it was accessible and functional. The user interface was kept minimal and intuitive, with clear navigation and large, easy-to-press buttons. This ensured users would find the app easy to use, even without technical expertise. By incorporating feedback from usability testing, the designs were refined to ensure an optimal user experience.

## Coding Process

To develop the app, I used **Java** and **Android Studio**. The main strategies I employed include:

- **Database Integration**: I created an SQLite database to store user data persistently. This was essential for maintaining data between app sessions.
- **Modular Code**: I structured the app into distinct modules for user authentication, database operations, and notification handling. This modular approach kept the code clean and maintainable.
- **Permissions Handling**: I used runtime permissions to request SMS access, ensuring the app behaves correctly even if permissions are denied.

The techniques I used in this project can be applied in future development work, especially the emphasis on database management, modular code structure, and permission handling. These are key practices for building reliable and scalable mobile applications.

## Testing and Validation

To ensure the functionality of the app, I conducted extensive testing using Android Studio’s Emulator. This testing focused on the following areas:

- **Login functionality**: Ensuring that users can securely log in and that the database correctly stores new user information.
- **Database operations**: Testing create, read, update, and delete (CRUD) operations to ensure the app can manipulate data correctly.
- **Permission handling**: Ensuring the app functions correctly whether the user grants or denies SMS permission.

Testing is a critical part of the development process as it reveals issues early and ensures that the app is functional in different scenarios. This process highlighted areas for optimization, such as database query efficiency and proper handling of edge cases, which I then addressed.

## Innovation and Challenges

One of the main challenges I faced was ensuring smooth communication between the app’s UI and the SQLite database, especially when handling real-time updates to the grid display. To overcome this, I implemented an efficient method for retrieving and displaying data that ensured the app remained responsive, even with larger datasets.

Additionally, integrating the SMS notification feature required handling user permissions dynamically. I had to design the app in such a way that it gracefully handles permission denial without compromising the overall functionality.

## Success and Reflection

I am particularly proud of how the app handles real-time updates and manages user data efficiently. The database CRUD operations are implemented well, and the app’s UI is intuitive and easy to navigate. I demonstrated strong skills in Android development, particularly in database management and permission handling. This project also reinforced the importance of testing and refining the user interface to ensure it meets user needs and expectations.

In conclusion, this project has been an excellent demonstration of my ability to create a fully functional, user-centered mobile app. The experience has provided me with valuable insights into mobile development, from design to coding to testing. I look forward to applying these skills in future projects.

