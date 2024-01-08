import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cashmanagerfront.domain.usecase.model.TransactionCallback
import com.example.cashmanagerfront.ui.navigation.Routes
import com.example.cashmanagerfront.ui.screens.Settings.SettingsViewModel
import com.example.cashmanagerfront.ui.screens.widgets.AppBarWidget
import com.example.cashmanagerfront.ui.screens.widgets.BackgroundApp
import com.example.cashmanagerfront.ui.screens.widgets.CustomText
import com.example.cashmanagerfront.ui.theme.RED
import com.example.cashmanagerfront.ui.utils.Constant
import com.example.cashmanagerfront.ui.utils.Strings

@Composable
fun SettingsScreen(navController: NavHostController) {
    val context: Context = LocalContext.current

    val viewModel: SettingsViewModel = remember { SettingsViewModel(context = context) }

    Surface(modifier = Modifier.fillMaxSize()) {
        BackgroundApp()


        Column(modifier = Modifier
            .padding(16.dp)
            .padding(horizontal = 30.dp)
        ) {

            AppBarWidget(
                navController = navController,
                title = Strings.APP_SETTINGS,
                showBackArrow = true,
                showIcon = false
            )

            if(Constant.IS_AUTHENTICATED.value) {
                LaunchedEffect(key1 = true) {
                    println("get transaction launch effect")
                    viewModel.getAllTransactions()
                }

                Spacer(modifier = Modifier.weight(1f))
                CustomText(
                    text = Strings.SETTINGS_SELLER_NAME,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 8.dp)
                )
                CustomText(
                    text = viewModel.getUsernameFromPreferences()!!.uppercase(),
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 12.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                if(viewModel.hadTransaction.value) {
                CustomText(
                    text = Strings.SETTINGS_TRANSACTIONS_LIST,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 8.dp)
                )
                    TransactionsList(transactions = viewModel.transactions.reversed())
                } else {
                    Text(text = "bateau")
                }
                Spacer(modifier = Modifier.weight(1f)) // Pushes the logout button to the bottom
                TextButton(
                    onClick = {
                        viewModel.logout()
                        navController.navigate(Routes.SHOP_SCREEN) {
                            popUpTo(Routes.SHOP_SCREEN) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                        CustomText(Strings.SETTINGS_DISCONNECT, color = RED)

                }

            } else {
                Spacer(modifier = Modifier.weight(1f))
                CustomText(
                    text = Strings.LOGIN_TITLE,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(50.dp))

                CustomText(text = Strings.LOGIN_FIELD_NAME, color = Color.White, modifier = Modifier.align(Alignment.Start))
                TextField(value = viewModel.name.value, modifier = Modifier.width(width = 350.dp), onValueChange = {value ->
                    viewModel.name.value = value
                })
                Spacer(modifier = Modifier.height(35.dp))
                CustomText(text = Strings.LOGIN_FIELD_PASSWORD, color = Color.White, modifier = Modifier.align(Alignment.Start))
                TextField(value = viewModel.password.value, modifier = Modifier.width(width = 350.dp), onValueChange = {value ->
                    viewModel.password.value = value
                })
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = {
                       viewModel.login(context = context)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    CustomText(Strings.SETTINGS_CONNECT, color = MaterialTheme.colorScheme.onError)
                }
            }
        }
    }
}

@Composable
fun TransactionsList(transactions: List<TransactionCallback>) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .height(400.dp)
                .fillMaxWidth()
        ) {
            items(transactions) { transaction ->
                TransactionItem(transaction)
                Divider()
            }
        }
}

@Composable
fun TransactionItem(transaction: TransactionCallback) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            CustomText(
                text = transaction.account.accountNumber,
                size = 16.sp,
            )
            CustomText(
                text = Strings.SETTINGS_NUMBER_TRANSACTION + transaction.id.toString(),
                size = 16.sp,
            )
        }
        CustomText(
            text = transaction.amount.toString(),
            size = 18.sp,
        )
    }
}
