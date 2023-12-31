import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cashmanagerfront.ui.screens.widgets.BackgroundApp
import com.example.cashmanagerfront.ui.screens.widgets.CustomText

@Composable
fun SettingsScreen(navController: NavHostController) {
    val sellerName = "Paul"
    val transactions = placeholderTransactions;
    Surface(modifier = Modifier.fillMaxSize()) {
        BackgroundApp()


        Column(modifier = Modifier
            .padding(16.dp)
        ) {
            CustomText(
                text = "Vendeur :",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp)
            )
            CustomText(
                text = sellerName,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 12.dp)
            )
            TransactionsList(transactions)
            Spacer(modifier = Modifier.weight(1f)) // Pushes the logout button to the bottom
            Button(
                onClick = {
                    // Handle logout logic
                    navController.popBackStack() // To navigate back to the login screen
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                CustomText("Déconnexion", color = MaterialTheme.colorScheme.onError)
            }
        }
    }
}

@Composable
fun TransactionsList(transactions: List<Transaction>) {
    LazyColumn (
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White),
    ) {
        items(transactions) { transaction ->
            TransactionItem(transaction)
            Divider()
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            CustomText(
                text = transaction.date,
                size = 16.sp
            )
            CustomText(
                text = transaction.id,
                size = 16.sp
            )
        }
        CustomText(
            text = transaction.amount,
            size = 18.sp
        )
    }
}

data class Transaction(
    val id: String,
    val date: String,
    val amount: String
)

val placeholderTransactions = listOf(
    Transaction(
        id = "TXN123456",
        date = "2023-12-31",
        amount = "89.99€"
    ),
    Transaction(
        id = "TXN123457",
        date = "2023-12-30",
        amount = "49.50€"
    ),
    Transaction(
        id = "TXN123458",
        date = "2023-12-29",
        amount = "15.75€"
    )
)