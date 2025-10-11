## 📱 Shopping List Demo App

A simple Android app that allows users to create a list of items, edit each item's quantity or delete it from the list. Originally built by following a video tutorial, then enhanced with personal touches and a modular MVVM architecture.

---

### 🚀 Features

- Add items to a shopping list
- Update the initial quantity
- Delete items instantly
- Real-time UI updates using Jetpack Compose
- MVVM architecture with modular controllers
- Clean separation of UI, state, and logic

---

### 🛠️ Tech Stack

| Tool / Library    | Purpose                    |
|-------------------|----------------------------|
| Kotlin            | Core programming language  |
| Jetpack Compose   | Declarative UI framework   |
| Android Studio    | Development environment    |
| MVVM              | Architecture pattern       |

---

### 📸 Screenshots

<div style="text-align: center; margin-bottom: 20px;">
  <div><strong>Main Screen</strong></div>
  <img src="demoImages/screenshot1.png" alt="Main Screen" width="200"/>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <div><strong>Filled Add Item Alert</strong></div>
  <img src="demoImages/screenshot2.png" alt="Filled Add Item Alert" width="200"/>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <div><strong>Added item message</strong></div>
  <img src="demoImages/screenshot3.png" alt="Added item message" width="200"/>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <div><strong>Items list</strong></div>
  <img src="demoImages/screenshot4.png" alt="Items list" width="200"/>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <div><strong>Editing an existing item</strong></div>
  <img src="demoImages/screenshot5.png" alt="Editing an existing item" width="200"/>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <div><strong>Updated list with edited item</strong></div>
  <img src="demoImages/screenshot6.png" alt="Updated list with edited item" width="200"/>
</div>

<div style="text-align: center; margin-bottom: 20px;">
  <div><strong>Deleting an item</strong></div>
  <img src="demoImages/screenshot7.png" alt="Deleting an item" width="200"/>
</div>

---

### 📦 Installation

```cmd
git clone https://github.com/gavri1990/ShoppingListDemoApp
```

Open the project in **Android Studio**, build, and run on an emulator or device.

---

### 🧪 Status

This project is a learning sandbox and not yet production-ready. The architecture now follows MVVM principles, with UI logic separated into composables, state managed via a ViewModel, and sub-controllers handling specific screen responsibilities.

---

### 🙌 Acknowledgments

- Jetpack Compose documentation
- Kotlin community for helpful resources
