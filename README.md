# e-Diagnostic-Pro 

> Application web Java de gestion du parcours de diagnostic medical : accueil patient par l'infirmier, consultations du medecin generaliste, tele-expertise avec specialistes, et pilotage admin.

[![Java](https://img.shields.io/badge/Java-17-orange)]() [![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-6.1-blue)]() [![Hibernate](https://img.shields.io/badge/Hibernate-6.4.4-green)]() [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-42.7.2-blue)]() [![Maven](https://img.shields.io/badge/Maven-WAR-red)]()

---

## Sommaire

- [Presentation](#presentation)
- [Fonctionnalites par role](#fonctionnalites-par-role)
- [Stack technique](#stack-technique)
- [Prerequis](#prerequis)
- [Installation](#installation--lancement)
- [Structure](#structure-du-projet)
- [Routes](#routes--servlets)
- [Modele de donnees](#modele-de-donnees)
- [Securite](#securite--authentification)

---

## Presentation

**e-Diagnostic-Pro** est une app web **Jakarta EE (Servlets + JSP + JPA/Hibernate)** packagee en **WAR** pour **Tomcat 10/11**.

Parcours patient :

1. **Infirmier** : enregistre le patient, saisit signes vitaux, gere file d'attente.
2. **Medecin Generaliste** : consulte la file, fait la consultation, cloture ou demande avis specialiste.
3. **Medecin Specialiste** : gere creneaux, traite demandes tele-expertise, donne avis + recommandations.
4. **Admin** : gere les utilisateurs, supervise via dashboard.

Architecture **MVC** : `JSP -> Servlet -> Service -> DAO (JPA) -> PostgreSQL`

## Fonctionnalites par role

### Auth commune
- `GET/POST /login` : connexion email + mot de passe
- `GET /logout` : deconnexion
- Redirection auto vers dashboard du role + filtre `AuthFilter` avec RBAC.

### ADMIN (`/admin/*`)
- `GET /admin/dashboard` : stats utilisateurs, patients, consultations
- `GET/POST /admin/utilisateurs/ajouter` : creation utilisateur `INFIRMIER, GENERALISTE, SPECIALISTE, ADMIN` + specialite/tarif si specialiste.

### INFIRMIER (`/infirmier/*`)
- `GET /infirmier/dashboard` : accueil + file d'attente
- `GET /infirmier/patients/*` : liste, recherche, detail
- `GET/POST /infirmier/patients/ajouter` : nouveau patient
- `GET/POST /infirmier/liste-attente` : file `EN_ATTENTE, EN_COURS, TERMINE, ANNULE`
- `GET/POST /patients/signeVitaux` : tension, temperature, FC, SpO2, glycemie, poids, taille...

### MEDECIN GENERALISTE (`/medecin/*`)
- `GET /medecin/dashboard` : patients en attente, consultations du jour
- `GET/POST /medecin/consultation` : cloturer / demander avis (`TERMINEE, EN_ATTENTE_AVIS_SPECIALISTE`)
- `GET /medecin/dossier/*` : dossier complet patient
- `GET/POST /medecin/tele-expertise/*` : question + donnees analyses + priorite + specialite cible

### SPECIALISTE (`/specialiste/*`)
- `GET /specialiste/dashboard` : demandes `EN_ATTENTE` + historique `TERMINEE`
- `GET/POST /specialiste/demande/*` : avisExpert + recommandations
- Creneaux : `LIBRE, RESERVE, ANNULE`

## Stack technique

| Couche | Techno |
|--------|--------|
| Langage | Java 17 |
| Web | Jakarta Servlet 6.1, JSP + JSTL 3.0 |
| Persistance | Hibernate 6.4.4, JPA 3.0 (`eDiagnosticProPU`) |
| BDD | PostgreSQL (driver 42.7.2) |
| Build | Maven WAR (`e-Diagnostic-Pro.war`) |
| Serveur | Tomcat 10.1+ / 11 |
| Tests | JUnit 5.10.2 + Mockito 5.11.0 |

## Prerequis

- JDK 17, Maven 3.8+, PostgreSQL 14+, Tomcat 10.1+

## Installation & Lancement

### 1. Cloner
```
git clone https://github.com/lakroune/e-Diagnostic-Pro.git
cd e-Diagnostic-Pro
```

### 2. BDD
```sql
CREATE DATABASE e_diagnostic_pro;
```

### 3. Config `src/main/resources/META-INF/persistence.xml`
```
url: jdbc:postgresql://localhost:5432/e_diagnostic_pro
user: postgres / password: 123456
hbm2ddl.auto: update
```

### 4. Build
```
mvn clean package
```

### 5. Deploy Tomcat
- Copier `target/e-Diagnostic-Pro.war` vers `webapps/`
- Ouvrir `http://localhost:8080/e-Diagnostic-Pro/login`

## Structure du projet

```
src/main/java/ma/youcode/
  controller/ -> Servlets (Auth, Admin, Infirmier, Medecin, Specialiste)
  service/    -> Logique metier
  dao/ + impl/-> JPA/Hibernate
  model/      -> Entites + enums
  config/     -> JPAUtil, JPAInitializer
src/main/resources/META-INF/persistence.xml
src/main/webapp/
  index.jsp, assets/, WEB-INF/views/{admin,infirmier,medecin,specialiste,login.jsp}
src/test/ -> JUnit5 + Mockito
```

## Routes / Servlets

| URL | Role |
|-----|------|
| `/login`, `/logout` | public / connecte |
| `/admin/dashboard`, `/admin/utilisateurs/ajouter` | ADMIN |
| `/infirmier/dashboard`, `/infirmier/patients/*`, `/infirmier/liste-attente` | INFIRMIER |
| `/patients`, `/patients/signeVitaux*` | INFIRMIER/MEDECIN |
| `/medecin/dashboard`, `/medecin/consultation`, `/medecin/dossier/*`, `/medecin/tele-expertise/*` | GENERALISTE |
| `/specialiste/dashboard`, `/specialiste/demande/*` | SPECIALISTE |

Filtre `AuthFilter` sur `/admin/*, /infirmier/*, /medecin/*, /specialiste/*`.

## Modele de donnees

- `Utilisateur` -> `Infirmier, MedecinGeneraliste, MedecinSpecialiste`
- `Patient`, `SigneVital`, `FileAttente`, `Consultation`, `ActeTechnique`, `DemandeTeleExpertise`, `Creneau`
- Enums: `Role (INFIRMIER, GENERALISTE, SPECIALISTE, ADMIN)`, `SpecialiteMedicale (CARDIOLOGIE, PNEUMOLOGIE...)`, `StatutConsultation`, `StatutExpertise`, `StatutFile`, `StatutCreneau`, `PrioriteExpertise`

## Securite & Authentification

- `AuthService + AuthDAO`, session `user`
- JSP sous `WEB-INF` accessibles uniquement via forward
- A ameliorer: hash BCrypt mots de passe, Flyway, pagination, WebSocket file d'attente

## Tests

```
mvn test
```

## Auteur

Projet YouCode `ma.youcode` v1.0-SNAPSHOT. Repo: `https://github.com/lakroune/e-Diagnostic-Pro.git`

