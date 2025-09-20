## 📱 Shopping List Demo App

A simple Android app that allows users to create a list of items and simultaneously edit or delete each one. Built by following a video tutorial and adding personal touches to optimize the reference code. The project emphasizes learning and experimentation, with all logic currently housed in `HomeItemCard.kt`.

---

### 🚀 Features

- Add items to a shopping list
- Edit item details inline
- Delete items instantly
- Real-time UI updates using Jetpack Compose
- Lightweight and beginner-friendly architecture

---

### 🛠️ Tech Stack

| Tool / Library                | Purpose                             |
|-------------------------------|-------------------------------------|
| Kotlin                        | Core programming language           |
| Jetpack Compose               | Declarative UI framework            |
| Android Studio                | Development environment             |
| `mutableStateOf` + `remember` | State management within composables |

---

### 📸 Screenshots

<div style="display: flex; flex-wrap: wrap; gap: 20px; justify-content: center;">
  <figure style="width: 200px; text-align: center;">
    <figcaption><strong>Main Screen</strong></figcaption>
    <img src="demoImages/screenshot1.png" alt="Main Screen" width="200"/>
  </figure>
  <figure style="width: 200px; text-align: center;">
    <figcaption><strong>Filled Add Item Alert</strong></figcaption>
    <img src="demoImages/screenshot2.png" alt="Filled Add Item Alert" width="200"/>
  </figure>
  <figure style="width: 200px; text-align: center;">
    <figcaption><strong>Added item message</strong></figcaption>
    <img src="demoImages/screenshot3.png" alt="Added item message" width="200"/>
  </figure>
  <figure style="width: 200px; text-align: center;">
    <figcaption><strong>Adding an already existing item</strong></figcaption>
    <img src="demoImages/screenshot4.png" alt="Adding an already existing item" width="200"/>
  </figure>
  <figure style="width: 200px; text-align: center;">
    <figcaption><strong>Existing item quantity update</strong></figcaption>
    <img src="demoImages/screenshot5.png" alt="Existing item quantity update" width="200"/>
  </figure>
  <figure style="width: 200px; text-align: center;">
    <figcaption><strong>Items list</strong></figcaption>
    <img src="demoImages/screenshot6.png" alt="Items list" width="200"/>
  </figure>
  <figure style="width: 200px; text-align: center;">
    <figcaption><strong>Editing an existing item</strong></figcaption>
    <img src="demoImages/screenshot7.png" alt="Editing an existing item" width="200"/>
  </figure>
  <figure style="width: 200px; text-align: center;">
    <figcaption><strong>Updated list with edited item</strong></figcaption>
    <img src="demoImages/screenshot8.png" alt="Updated list with edited item" width="200"/>
  </figure>
  <figure style="width: 200px; text-align: center;">
    <figcaption><strong>Deleting an item</strong></figcaption>
    <img src="demoImages/screenshot9.png" alt="Deleting an item" width="200"/>
  </figure>
</div>
---

### 📦 Installation

```cmd
git clone https://github.com/gavri1990/ShoppingListDemoApp
```

Open the project in **Android Studio**, build, and run on an emulator or device.

---

### 🧪 Status

This project is a learning sandbox and not yet production-ready. All logic is centralized in `HomeItemCard.kt` for simplicity and experimentation.

---

### 🙌 Acknowledgments

- Jetpack Compose documentation
- Kotlin community for helpful resources
