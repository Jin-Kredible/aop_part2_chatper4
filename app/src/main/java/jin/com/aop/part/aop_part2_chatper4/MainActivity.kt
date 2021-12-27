package jin.com.aop.part.aop_part2_chatper4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    private val expressionTextView : TextView by lazy {
        findViewById(R.id.expressionTextView)
    }

    private val resultTextView : TextView by lazy {
        findViewById(R.id.resultTextView)
    }

    private var isOperator =false
    private var hasOperator =false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun buttonClicked(v: View) {

        when(v.id) {
            R.id.button0 -> numberButtonClicked("0")
                R.id.button1 -> numberButtonClicked("1")
            R.id.button2 -> numberButtonClicked("2")
            R.id.button3 -> numberButtonClicked("3")
            R.id.button4 -> numberButtonClicked("4")
            R.id.button5 -> numberButtonClicked("5")
            R.id.button6 -> numberButtonClicked("6")
            R.id.button7 -> numberButtonClicked("7")
            R.id.button8 -> numberButtonClicked("8")
            R.id.button9 -> numberButtonClicked("9")
            R.id.buttonPlus -> numberButtonClicked("+")
                R.id.buttonDivide -> operatorButtonClicked("/")
            R.id.buttonMinus -> operatorButtonClicked("-")
            R.id.buttonModulo -> operatorButtonClicked("%")
            R.id.buttonMulti -> operatorButtonClicked("+")
        }

    }

    fun numberButtonClicked(number: String) {

        if(isOperator) {
            expressionTextView.append(" ")
        }

        isOperator = false

        val expressionText = expressionTextView.text.split(" ")
        if(expressionText.isNotEmpty() && expressionText.last().length>=15) {
            Toast.makeText(this,"15까지만 가능합니다",Toast.LENGTH_SHORT).show()
            return
        } else if (number == "0" && expressionText.last().isEmpty()) {
            Toast.makeText(this,"0은 제일 앞에 올 수 없습니다",Toast.LENGTH_SHORT).show()
            return
        }


        expressionTextView.append(number)



    }

    private fun operatorButtonClicked(operator: String) {

        if (expressionTextView.text.isEmpty()) {
            return
        }

        when {
            isOperator -> {
                val text = expressionTextView.text.toString()
                expressionTextView.text = text.dropLast(1)+operator
            }

            hasOperator -> {
                Toast.makeText(this,"연산자는 한번만 사용가능",Toast.LENGTH_SHORT).show()
                return

            }

            else -> {
                expressionTextView.append(" $operator")

            }



        }

        val ssb = SpannableStringBuilder(expressionTextView.text)
        ssb.setSpan(
            ForegroundColorSpan(getColor(R.color.green)),
            expressionTextView.text.length -1,
            expressionTextView.text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        expressionTextView.text = ssb
        isOperator = true
        hasOperator = true

    }

    fun resultButtonClicked(v: View) {



    }

    fun historyButtonClicked(v: View) {



    }

    fun clearButtonClicked(v: View) {



    }
}