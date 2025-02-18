## Description
MU GUDA is a mobile application designed for buying and selling agricultural products with seamless payment integration. The Mekelle Universty and Community can connect, browse available products, and complete purchases using various payment methods like Telebirr, Chappa, and Santim Pay(no API integrated).


## Constructive Criticism I Was Given
1. Integrate multiple payment gateways for user convenience.
2. add image display
3. Implement cart 

## Features
- Store product information and user purchase history locally.
- Upload purchase data to Firebase Firestore.
- Display available agricultural products with descriptions and prices.
- Payment method integration with Telebirr, Chappa, and Santim Pay.
- Generate transaction details (transaction code, time, method, and receiver number) before calling the API.
- Store transaction data in Firestore for tracking and verification.
- Display success or failure messages after payment processing.

## Technologies Used
- **Programming Language:** Java/Kotlin
- **IDE:** Android Studio
- **Firebase:** for Authentication 
- **Database for Products and Transactions:** Firebase Firestore
- **Display Product:** Glide

## Installation
1. Clone this repository.
2. Open the project in Android Studio.
3. Sync Gradle files.
4. Run the app on an emulator or Android device.
5. Create an account or log in using email and password.
## Developed By
- Name: Nahom Tadesse
- Email: nahomtadesse93@gmail.com
