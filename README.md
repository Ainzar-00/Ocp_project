# 📱 OCP Evaluation Formation — Android Application

![Android](https://img.shields.io/badge/Platform-Android-green?logo=android)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple?logo=kotlin)
![Architecture](https://img.shields.io/badge/Architecture-MVVM-orange)
![Database](https://img.shields.io/badge/Local%20DB-Room-blue)
![Backend](https://img.shields.io/badge/Cloud-Firebase%20%2F%20Apps%20Script-yellow?logo=firebase)

> **OCP Evaluation Formation** is a native Android application designed to digitize, automate, and manage training evaluation processes within the industrial complex **OCP Maroc Phosphore (Safi)**. Built aligned with OCP's **"Digital Era"** vision to transition from paper-based workflows to fully digital, real-time analytics.

---

## 📌 Features

### 🏢 HR Dashboard & Analytics
* **Real-time Key Performance Indicators (KPIs):** Track coverage rates, total evaluations, pending FLM requests, and completed themes at a glance.
* **Data Visualization:** Interactive charts using **Radar Charts** (evaluating Need, Impact, Application, and Overall satisfaction) and **Donut Charts** (Satisfaction distribution).

### 📑 Evaluation & Entity Management
* **Offline-First Capabilities:** Data is stored locally using **Room Database** and automatically synced to **Firebase Cloud Firestore** once connected.
* **Mass Data Import:** Import employees, trainers, FLM managers, and training catalogs directly via Excel files (`.xlsx`) powered by **Apache POI**.
* **Automation:** Google Apps Script integration to generate Google Forms links directly for instant evaluations.

### ✉️ Communication & Reporting
* **Invitation Tracking:** Monitor invitation statuses (`Pending`, `Sent`, `Not Sent`) with dynamic search and filtering tools.
* **Email Integration:** Send automated invitation emails and reports directly via **JavaMail (SMTP)**.
* **Excel Exporting:** Export aggregated evaluation summaries into structured Excel spreadsheets for HR archives.

---

## 🛠️ Tech Stack & Architecture

### **Architecture Pattern**
* **MVVM (Model-View-ViewModel)** for strict separation of concerns, testability, and UI responsiveness.

### **Core Technologies**
* **Language:** Kotlin
* **UI Components:** Jetpack Views, LiveData, ViewModel, Navigation Component, Material Design
* **Local Storage:** Room Persistence Library (SQLite abstraction)
* **Cloud Backend & Sync:** Firebase Authentication, Cloud Firestore, Firebase Cloud Messaging (FCM)
* **Background Tasks:** WorkManager (for robust data synchronization)
* **Network & External Services:** Retrofit2, OkHttp, Google Apps Script Web API, JavaMail API
* **File Handling:** Apache POI (Excel `.xlsx` reader/writer)

---

## 📐 System Architecture

```mermaid
graph TD
    subgraph UI ["UI Layer"]
        View["VIEW - Activities / Fragments / XML Layouts"]
    end

    subgraph Architecture ["Architecture Core"]
        ViewModel["VIEWMODEL - Prepares UI Data & Handles Logic"]
        Repository["REPOSITORY"]
    end

    subgraph Data ["Data Layer"]
        LocalDS["Local Data Source - Room DB"]
        RemoteDS["Remote Data Source - Cloud Firestore"]
    end

    View -->|Observes LiveData/Flow| ViewModel
    ViewModel -->|Data Stream| Repository
    Repository --> LocalDS
    Repository --> RemoteDS
