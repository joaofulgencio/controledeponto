package config.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import io.grpc.LoadBalancerRegistry
import io.grpc.internal.PickFirstLoadBalancerProvider
import java.io.ByteArrayInputStream
import java.io.FileInputStream

fun firebaseInitializer() : Firestore {
//    val currentWorkingDirectory = System.getProperty("user.dir")
//    val filePath = "$currentWorkingDirectory/src/main/kotlin/config/firebase/controledeponto.json"
//    val serviceAccount = FileInputStream(filePath)
//
//    val options =  FirebaseOptions.builder()
//        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//        .build()
//    FirebaseApp.initializeApp(options)
//
//    // Check if FirebaseApp is not already initialized; if not, initialize it.
//    FirebaseApp.getApps().isEmpty().takeIf { it }?.let { FirebaseApp.initializeApp(options) }
//
//    return FirestoreClient.getFirestore()

    val privateKey = System.getenv("FIREBASE_PRIVATE_KEY").replace("\\n", "\n")
    val config = """
        {
            "type": "${System.getenv("FIREBASE_TYPE")}",
            "project_id": "${System.getenv("FIREBASE_PROJECT_ID")}",
            "private_key_id": "${System.getenv("FIREBASE_PRIVATE_KEY_ID")}",
            "private_key": "$privateKey",
            "client_email": "${System.getenv("FIREBASE_CLIENT_EMAIL")}",
            "client_id": "${System.getenv("FIREBASE_CLIENT_ID")}",
            "auth_uri": "${System.getenv("FIREBASE_AUTH_URI")}",
            "token_uri": "${System.getenv("FIREBASE_TOKEN_URI")}",
            "auth_provider_x509_cert_url": "${System.getenv("FIREBASE_AUTH_PROVIDER_X509_CERT_URL")}",
            "client_x509_cert_url": "${System.getenv("FIREBASE_CLIENT_X509_CERT_URL")}",
            "universe_domain": "${System.getenv("FIREBASE_UNIVERSE_DOMAIN")}"
        }
    """.trimIndent()

    val serviceAccount = ByteArrayInputStream(config.toByteArray())

    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build()

    FirebaseApp.initializeApp(options)

    FirebaseApp.getApps().isEmpty().takeIf { it }?.let { FirebaseApp.initializeApp(options) }
    return FirestoreClient.getFirestore()
}
