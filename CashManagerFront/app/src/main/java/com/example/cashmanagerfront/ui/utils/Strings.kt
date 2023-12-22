package com.example.cashmanagerfront.ui.utils

object Strings {

    const val APP_TITLE = "Cash Manager"

    // APP BAR
    const val APP_BAR_TOTAL = "Paiement"
    const val APP_BAR_SHOP = "Sélection d'articles"
    const val APP_BAR_PAYOUT_CARD = "Paiement sans contact"
    const val APP_BAR_PAYOUT_QR = "Paiement par chèque"


    // WELCOME_SCREEN
    const val WELCOME_TITLE = "Bonjour à tous !"

    // TOTAL_PAYOUT_SCREEN
    const val TOTAL_PAYOUT_BODY = "Vous devez :"
    const val TOTAL_PAYOUT_CURRENCY = "€"
    const val TOTAL_PAYOUT_CHOICE_SYSTEM = "Sélectionnez votre moyen de paiement"
    const val TOTAL_PAYOUT_CHOICE_SYSTEM_CARD = "Sans contact"
    const val TOTAL_PAYOUT_CHOICE_SYSTEM_QR = "Chèque"

    const val GO_SHOP = "Retourner au magasin"


    // PAYOUT_NFC_SCREEN
    const val NFC_INITIAL = "Merci de présenter votre carte"
    const val NFC_PENDING_DATA = "Récupération des données"
    const val NFC_PENDING = "Autorisation en cours. Veuillez patienter"
    const val NFC_ACCEPTED = "Paiement accepté"
    const val NFC_REFUSED = "Paiement refusé, veuillez réessayer"
    const val RETRY_SCAN = "Réessayer"

    const val QR_INITIAL = "Merci de scanner votre chèque"
    const val QR_ERROR = "Montant incorrect, veuillez remplir le bon montant"
    const val QR_CORRECT = "Montant validé"
}