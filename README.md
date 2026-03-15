# OCP Évaluation Formations — Application Mobile Android

## 📋 Vue d'ensemble

Application mobile Android pour la digitalisation du processus d'évaluation des formations au sein du **Groupe OCP**.

### Flux principal

```
RH importe Bilan FC (Excel)
        ↓
RH envoie un email au FLM avec un lien Google Form pré-rempli
        ↓
FLM reçoit l'email et clique sur le lien Google Form
        ↓
FLM remplit et soumet le formulaire Google
        ↓
(Via Firebase Cloud Functions / webhook) → Évaluation sauvegardée dans Firebase + Room
        ↓
RH consulte les statistiques, graphiques, et peut exporter en Excel
```

---

## 🏗️ Architecture

| Couche | Technologie |
|--------|------------|
| UI | Android Views + ViewBinding + Navigation Component |
| ViewModel | Hilt + ViewModel + LiveData + StateFlow |
| Local | **Room** (SQLite) |
| Cloud | **Firebase** (Auth, Firestore, Cloud Messaging) |
| Email | Android Email Intent (+ optionnel: Firebase Functions + SendGrid) |
| Formulaire | **Google Forms** (lien pré-rempli) |
| Charts | **MPAndroidChart** (Radar, Pie, Bar, Line) |
| Export | **Apache POI** (xlsx) |

---

## ⚙️ Configuration requise

### 1. Firebase Setup

1. Créez un projet Firebase sur https://console.firebase.google.com
2. Ajoutez une application Android avec le package `com.ocp.evalformation`
3. Téléchargez `google-services.json` et placez-le dans `app/`
4. Activez dans la console Firebase :
   - **Authentication** → Email/Password
   - **Firestore Database** → Mode production
   - **Cloud Messaging**

#### Structure Firestore

```
users/
  {uid}/
    role: "RH" | "FLM" | "ADMIN"
    nom: string
    matricule: string
    email: string

collaborateurs/
  {matricule}/
    matricule, nom, prenom, entite, division,
    theme, mois, objectifsPedagogiques, formateur, annee

evaluations/
  {id}/
    matricule, theme, dateEvaluation, flmMatricule, flmNom,
    organisationContenu (1-4), qualitePedagogique (1-4), ...
    propositionsAmelioration, competencesAcquises, commentaireGeneral,
    statut, createdAt, googleFormResponseId

invitations_flm/
  {id}/
    matriculeCollaborateur, nomCompletCollaborateur, theme,
    emailFlm, nomFlm, googleFormUrl, statut, dateEnvoi

stats_theme/
  {theme_annee}/
    theme, annee, nbParticipants, nbEvaluations, tauxReponse,
    moyOrganisationContenu, ..., moyenneGlobale, tauxSatisfactionPct
```

#### Règles Firestore (firestore.rules)

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Users can only read their own data
    match /users/{uid} {
      allow read: if request.auth.uid == uid;
    }
    // RH can read/write all; FLM can read collaborateurs
    match /collaborateurs/{doc} {
      allow read: if request.auth != null;
      allow write: if get(/databases/$(database)/documents/users/$(request.auth.uid)).data.role == 'RH';
    }
    match /evaluations/{doc} {
      allow read, write: if request.auth != null;
    }
    match /invitations_flm/{doc} {
      allow read, write: if request.auth != null;
    }
    match /stats_theme/{doc} {
      allow read: if request.auth != null;
      allow write: if get(/databases/$(database)/documents/users/$(request.auth.uid)).data.role == 'RH';
    }
  }
}
```

---

### 2. Google Forms Setup

#### Créer le formulaire d'évaluation

1. Allez sur https://forms.google.com
2. Créez un nouveau formulaire avec ces champs :

| Champ | Type | Description |
|-------|------|-------------|
| Matricule collaborateur | Réponse courte | Pré-rempli automatiquement |
| Nom complet | Réponse courte | Pré-rempli automatiquement |
| Thème de formation | Réponse courte | Pré-rempli automatiquement |
| Entité | Réponse courte | Pré-rempli automatiquement |
| Formateur | Réponse courte | Pré-rempli automatiquement |
| Objectifs pédagogiques | Paragraphe | Pré-rempli automatiquement |
| Organisation et contenu | Échelle 1-4 | À remplir par le FLM |
| Qualité pédagogique | Échelle 1-4 | À remplir par le FLM |
| Adaptation au public | Échelle 1-4 | À remplir par le FLM |
| Maîtrise du sujet | Échelle 1-4 | À remplir par le FLM |
| Disponibilité du formateur | Échelle 1-4 | À remplir par le FLM |
| Qualité des supports | Échelle 1-4 | À remplir par le FLM |
| Atteinte des objectifs | Échelle 1-4 | À remplir par le FLM |
| Application terrain | Échelle 1-4 | À remplir par le FLM |
| Propositions d'amélioration | Paragraphe | À remplir par le FLM |
| Compétences acquises | Paragraphe | À remplir par le FLM |
| Commentaire général | Paragraphe | À remplir par le FLM |

3. Cliquez sur **"⋮" → Obtenir un lien pré-rempli**
4. Remplissez les champs, cliquez **Obtenir le lien**, puis inspectez l'URL pour extraire les `entry.XXXXXXXXX`
5. Mettez à jour `GoogleFormHelper.kt` :

```kotlin
const val GOOGLE_FORM_BASE_URL = "https://docs.google.com/forms/d/e/VOTRE_ID/viewform"
const val ENTRY_MATRICULE = "entry.VOTRE_ENTRY_ID"
// ... etc
```

#### (Optionnel) Récupérer les réponses automatiquement via Apps Script

Pour synchroniser automatiquement les réponses Google Form dans Firebase :

1. Dans Google Forms → **⋮ → Éditeur de script**
2. Collez ce code :

```javascript
function onFormSubmit(e) {
  const resp = e.response;
  const answers = resp.getItemResponses();
  
  const data = {
    matricule: answers[0].getResponse(),
    theme: answers[2].getResponse(),
    flmNom: "FLM",
    dateEvaluation: new Date().toISOString(),
    organisationContenu: parseInt(answers[6].getResponse()),
    qualitePedagogique: parseInt(answers[7].getResponse()),
    adaptationPublic: parseInt(answers[8].getResponse()),
    maitriseSujet: parseInt(answers[9].getResponse()),
    disponibiliteFormateur: parseInt(answers[10].getResponse()),
    qualiteSupports: parseInt(answers[11].getResponse()),
    atteinteObjectifs: parseInt(answers[12].getResponse()),
    applicationTerrain: parseInt(answers[13].getResponse()),
    propositionsAmelioration: answers[14].getResponse(),
    competencesAcquises: answers[15].getResponse(),
    commentaireGeneral: answers[16].getResponse(),
    statut: "SOUMIS",
    createdAt: Date.now(),
    googleFormResponseId: resp.getId()
  };
  
  // POST to Firebase via REST API
  const url = "https://firestore.googleapis.com/v1/projects/VOTRE_PROJECT_ID/databases/(default)/documents/evaluations";
  UrlFetchApp.fetch(url, {
    method: "POST",
    contentType: "application/json",
    payload: JSON.stringify({ fields: toFirestoreFields(data) })
  });
}

function toFirestoreFields(obj) {
  const fields = {};
  for (const [k, v] of Object.entries(obj)) {
    if (typeof v === 'string') fields[k] = { stringValue: v };
    else if (typeof v === 'number') fields[k] = { integerValue: v };
  }
  return fields;
}
```

3. Configurez un **déclencheur** : `onFormSubmit` → À la soumission du formulaire

---

### 3. Comptes Firebase Auth

Créez dans **Firebase Auth → Gérer les utilisateurs** :

| Email | Rôle |
|-------|------|
| rh@ocp.ma | RH |
| flm@ocp.ma | FLM |
| admin@ocp.ma | ADMIN |

Puis dans Firestore `users/{uid}` ajoutez `{ "role": "RH" }` etc.

---

## 🚀 Compilation & Installation

```bash
# Cloner le projet
git clone <repo>
cd OCP_EvalApp

# Ajouter google-services.json dans app/
cp /path/to/google-services.json app/

# Configurer GoogleFormHelper.kt avec vos entry IDs

# Compiler
./gradlew assembleDebug

# Installer sur device
./gradlew installDebug
```

---

## 📱 Fonctionnalités

### Espace RH
| Écran | Fonctionnalités |
|-------|----------------|
| 📊 Tableau de bord | KPIs : nb collaborateurs, évaluations, satisfaction moy., invitations en attente |
| 📁 Import Bilan FC | Import Excel (.xlsx), comptage, test matricule, chargement Firebase |
| 📧 Invitations FLM | Envoi email avec lien Google Form pré-rempli, suivi statut (en attente/répondu) |
| 📈 Graphiques | Radar, camembert, barres, courbe temporelle, propositions d'amélioration |
| 📋 Évaluations | Filtrage (thème/mois/année), détail par évaluation, export Excel, suppression |

### Espace FLM
| Écran | Fonctionnalités |
|-------|----------------|
| 🔍 Recherche | Recherche par matricule, affichage infos collaborateur + thème |
| 📝 Formulaire | Ouvrir le Google Form dans le navigateur ou copier le lien |

### Stockage local (Room)
- Toutes les données disponibles **hors ligne**
- Synchronisation automatique vers Firebase quand réseau disponible

---

## 📁 Structure du projet

```
app/src/main/java/com/ocp/evalformation/
├── OcpApplication.kt
├── data/
│   ├── local/
│   │   ├── OcpDatabase.kt           ← Room DB
│   │   ├── dao/Daos.kt              ← Collaborateur, Evaluation, Invitation, Stats DAOs
│   │   └── entity/Entities.kt       ← Room entities
│   ├── remote/
│   │   └── FirebaseRepository.kt    ← All Firebase ops
│   └── repository/
│       └── MainRepository.kt        ← Combines Room + Firebase
├── di/
│   └── AppModule.kt                 ← Hilt DI
├── model/
│   └── AppUser.kt                   ← UserRole enum
├── ui/
│   ├── auth/LoginActivity.kt
│   ├── rh/
│   │   ├── RhActivity.kt
│   │   ├── RhViewModel.kt
│   │   ├── dashboard/DashboardFragment.kt
│   │   ├── dashboard/InvitationsFragment.kt
│   │   ├── import_bilan/ImportBilanFragment.kt
│   │   ├── charts/ChartsFragment.kt
│   │   └── evaluations/EvaluationsFragment.kt
│   └── flm/FlmActivity.kt
└── utils/
    ├── EmailHelper.kt               ← Email intent builder
    ├── ExcelHelper.kt               ← Apache POI import/export
    ├── GoogleFormHelper.kt          ← Pre-filled form URL builder
    └── OcpFirebaseMessagingService.kt
```

---

## 🔧 Variables à configurer

| Fichier | Variable | Description |
|---------|----------|-------------|
| `app/google-services.json` | — | Fichier Firebase (à télécharger) |
| `GoogleFormHelper.kt` | `GOOGLE_FORM_BASE_URL` | URL de votre Google Form |
| `GoogleFormHelper.kt` | `ENTRY_*` | IDs des champs du form |

---

## 📊 Critères d'évaluation (échelle 1-4)

| Score | Signification |
|-------|--------------|
| 1 | Insuffisant |
| 2 | À améliorer |
| 3 | Satisfaisant |
| 4 | Très satisfaisant |

Taux de satisfaction = (Moyenne des scores / 4) × 100%
