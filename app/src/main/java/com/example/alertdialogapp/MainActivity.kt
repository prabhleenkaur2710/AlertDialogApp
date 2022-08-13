package com.example.alertdialogapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.alertdialogapp.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity() {
    lateinit var btnAlertDialog: Button
    lateinit var btnAlertListDialog: Button
    lateinit var btnCustomDialog: Button
    lateinit var tvCustomDialogText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAlertDialog = findViewById(R.id.btnAlertDialog)
        btnAlertListDialog = findViewById(R.id.btnListAlertDialog)
        btnCustomDialog = findViewById(R.id.btnCustomDialog)
        tvCustomDialogText = findViewById(R.id.tvCustomDialogText)

        btnAlertDialog.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Alert Dialog")
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Alert Dialog Message")
            alertDialog.setPositiveButton("Positive"){_,_->
                Toast.makeText(this, "this is positive button toast", Toast.LENGTH_LONG).show()
            }
            alertDialog.setNegativeButton("Negative"){_,_->}
            alertDialog.setNeutralButton("Neutral"){_,_->}
            alertDialog.show()
        }

        btnAlertListDialog.setOnClickListener {
            var array = arrayOf("R","P","A")
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("List alert")
            alertDialog.setItems(array,{_,position->
                Toast.makeText(this, "item at position ${array[position]}", Toast.LENGTH_LONG).show()

            })
            alertDialog.show()
        }

        btnCustomDialog.setOnClickListener {
            var dialog = Dialog(this)
            var dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.setCancelable(false)
            dialog.window?.setLayout(MATCH_PARENT,WRAP_CONTENT )
            dialogBinding.tvTitle.setText("Title")
            dialogBinding.tvMessage.setText("Enter name")
            dialogBinding.etName.setText(tvCustomDialogText.text)
            dialogBinding.btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialogBinding.btnOk.setOnClickListener {
               if( dialogBinding.etName.text.toString().isNullOrEmpty()){
                   Toast.makeText(this, "Enter name", Toast.LENGTH_LONG).show()
               }else{
                   tvCustomDialogText.setText( dialogBinding.etName.text.toString())
                   dialog.dismiss()

               }
            }
            dialog.show()

        }
    }
}