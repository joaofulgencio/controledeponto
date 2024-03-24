package config.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import java.io.FileInputStream

fun firebaseInitializer() : Firestore {
    val currentWorkingDirectory = System.getProperty("user.dir")
    val filePath = "$currentWorkingDirectory/src/main/kotlin/config/firebase/controledeponto.json"
    val serviceAccount = FileInputStream(filePath)

    val options =  FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build()
    FirebaseApp.initializeApp(options)

    // Check if FirebaseApp is not already initialized; if not, initialize it.
    FirebaseApp.getApps().isEmpty().takeIf { it }?.let { FirebaseApp.initializeApp(options) }

    return FirestoreClient.getFirestore()
}
