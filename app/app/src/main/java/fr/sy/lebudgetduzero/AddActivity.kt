package fr.sy.lebudgetduzero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import fr.sy.lebudgetduzero.database.AppDatabase
import fr.sy.lebudgetduzero.item.IncomeItem
import fr.sy.lebudgetduzero.item.SpentItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

    fun addTransaction(view: View){
        val editName=findViewById<EditText>(R.id.editNameItem).text.toString()
        val editValue=findViewById<EditText>(R.id.editValue).text.toString().toFloat()
        //val editDate= Integer.parseInt(findViewById<EditText>(R.id.editDate).toString())
        val editType=Integer.parseInt(findViewById<EditText>(R.id.editType).text.toString())

        val isDepense=findViewById<RadioButton>(R.id.isDepense).isChecked

        val applicationScope = CoroutineScope(SupervisorJob())
        applicationScope.launch {

            //Db
            val db = AppDatabase.getDatabase(applicationContext)

            if(isDepense){
                db.spentDao().insertAll(SpentItem(name=editName,value=editValue,id_type=editType,date=1654847942))
            }else{
                db.incomeDao().insertAll(IncomeItem(name=editName,value=editValue,date=1654847942))
            }

            //Redirection
            val intent= Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}