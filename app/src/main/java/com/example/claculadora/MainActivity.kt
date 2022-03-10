package com.example.claculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.claculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding //7 de marzo
    var x: Double = 0.0
    var y: Double = 0.0
    var op: Char = '0'
    var opx: Char = '0'
    var res: Double = 0.0
    var igu: Double = 0.0
    var igl: Boolean = false
    var opr: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnAc.setOnClickListener {
            if (opr) {
                binding.value.text.clear()
                binding.valuer.text.clear()
            }
            if (igl) {
                binding.value.text.clear()
                binding.valuer.text.clear()
                binding.textView.text = ""
                igl = false
                opr = false
            }
            binding.value.text.clear()
        }
        binding.btnDel.setOnClickListener {
            if (igl) {
                binding.textView.text = ""
                binding.value.text.clear()
                igl = false
                opr = false
            }
            else if (opr) {

            }
            binding.value.setText(binding.value.text.dropLast(1))
        }
    }
    fun clickButton(view: View) {
        val button = view as Button
        if (igl){
            binding.value.text.clear()
            binding.valuer.text.clear()
            binding.textView.text = ""
        }
        igl = false
        opr = false

        binding.valuer.text.clear()
        if (button.id == binding.pnt.id){
            if (!binding.value.text.contains('.') && binding.value.text.isNotEmpty()){
                binding.value.text.append('.')
            }
        }
        else{
            binding.value.text.append(button.text)
        }
    }
    fun operation(view: View) {
        val button = view as Button
        op = when(button.id) {
            binding.btnDiv.id -> {'/'}
            binding.btnMul.id -> {'*'}
            binding.btnRes.id -> {'-'}
            binding.btnSum.id -> {'+'}
            binding.btnPrt.id -> {'%'}
            binding.btnEnt.id -> {'='}
            else -> {'0'}
        }
        if (op != '=' && op != '%' && binding.valuer.text.isNotEmpty()){
            igl = false
            opr = false
            binding.textView.text = ""
            binding.value.text.clear()
            binding.value.setText(binding.valuer.text)
        }
        if (op == '=' && binding.valuer.text.isNotEmpty()) {
            igl = true
            opr = false
            binding.value.text.clear()
            binding.value.setText(binding.valuer.text)
            binding.value.text.clear()
            when(opx) {
                '/' -> {x /= y}
                '*' -> {x *= y}
                '-' -> {x -= y}
                '+' -> {x += y}
                else -> {'0'}
            }
            binding.valuer.setText("$x")
            binding.textView.text = "$x $opx $y = "
        }
        if (!opr && !igl) {
            if (binding.textView.text.isNotEmpty()) {
                res = x
                y = binding.value.text.toString().toDouble()
                binding.value.text.clear()
                when(opx) {
                    '/' -> {x /= y}
                    '*' -> {x *= y}
                    '-' -> {x -= y}
                    '+' -> {x += y}
                    else -> {'0'}
                }
                if (op != '=' && op != '%'){
                    opr = true
                    igl = false
                    binding.valuer.setText("$x")
                    binding.textView.text = "$x $op"
                    opx = op
                }
                else if (op == '%'){
                    igl = true
                    opr = false
                    x = res
                    when(opx) {
                        '/' -> {res /= ((res / 100) * y)}
                        '*' -> {res *= ((res / 100) * y)}
                        '-' -> {res -= ((res / 100) * y)}
                        '+' -> {res += ((res / 100) * y)}
                        else -> {}
                    }
                    binding.valuer.setText("$res")
                    binding.textView.text = "$x $opx $y % = "
                    opx = op
                }
                else{
                    igl = true
                    opr = false
                    binding.valuer.setText("$x")
                    binding.textView.text = "$x $opx $y = "
                    igu = y
                }
            }
            else if (binding.value.text.isNotEmpty()) {
                if (op != '='){
                    opr = true
                    igl = false
                    x = binding.value.text.toString().toDouble()
                    binding.textView.text = "$x $op "
                    binding.value.text.clear()
                    opx = op
                }
            }
        }

    }
}