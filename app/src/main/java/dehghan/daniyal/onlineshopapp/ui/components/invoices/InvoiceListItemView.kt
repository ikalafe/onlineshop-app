package dehghan.daniyal.onlineshopapp.ui.components.invoices

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import dehghan.daniyal.onlineshopapp.models.invoices.Invoice
import dehghan.daniyal.onlineshopapp.ui.components.AdvanceButton
import dehghan.daniyal.onlineshopapp.ui.theme.Red

@Composable
fun InvoiceListItemView(invoice: Invoice, navController: NavController) {
    AdvanceButton(
        title = invoice.addDate!!,
        subTitle = invoice.status!!,
        imageVector = if (invoice.paymentDate!!.isEmpty()) Icons.Filled.Close else Icons.Filled.Check,
        iconBackgroundColor = if (invoice.paymentDate!!.isEmpty()) Red else Color.Green
    ) {
        navController.navigate("invoice/${invoice.id}")
    }
}