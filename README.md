## 📱 Shopping List Demo App

A simple Android app that allows users to create a list of items, edit each item's quantity or delete it from the list. Originally built by following a video tutorial, then enhanced with personal touches and a modular MVVM architecture. Now includes local data persistence using Room Database.

---

### 🚀 Features

- Add items to a shopping list
- Update the initial quantity
- Delete items instantly
- Real-time UI updates using Jetpack Compose
- MVVM architecture with modular controllers
- Clean separation of UI, state, and logic
- Local data persistence with Room Database


---

### 🛠️ Tech Stack

| Tool / Library    | Purpose                         |
|-------------------|---------------------------------|
| Kotlin            | Core programming language       |
| Jetpack Compose   | Declarative UI framework        |
| Android Studio    | Development environment         |
| MVVM              | Architecture pattern            |
| Room              | Local database for persistence  |

---

### 📸 Screenshots

<div style="text-align: center; margin-bottom: 20px;">
  <div><strong>Main Screen</strong></div>
  <img src="demoImages/screenshot1.png" alt="Main Screen" width="200"/>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <div><strong>Add Item Dialog</strong></div>
  <img src="demoImages/screenshot2.png" alt="Add Item Dialog" width="200"/>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <div><strong>Items list</strong></div>
  <img src="demoImages/screenshot3.png" alt="Items list" width="200"/>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <div><strong>Edited item quantities</strong></div>
  <img src="demoImages/screenshot4.png" alt="Edited item quantities" width="200"/>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <div><strong>Deleted item</strong></div>
  <img src="demoImages/screenshot5.png" alt="Deleted item" width="200"/>
</div>

---

### 📦 Installation

```cmd
git clone https://github.com/gavri1990/ShoppingListDemoApp
```

Open the project in **Android Studio**, build, and run on an emulator or device.

---

### 🧪 Status

This project is a learning sandbox and not yet production-ready. The architecture now follows MVVM principles, with UI logic separated into composables, state managed via a ViewModel, and sub-controllers handling specific screen responsibilities. Room is used to persist shopping list data across app restarts.

---

### 🙌 Acknowledgments

- Jetpack Compose documentation
- Kotlin community for helpful resources
