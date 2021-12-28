package com.adhimbagas.finalprojectskripsi.ui

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.adhimbagas.finalprojectskripsi.MainActivity
import com.adhimbagas.finalprojectskripsi.R
import com.adhimbagas.finalprojectskripsi.databinding.FragmentLaporBinding
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText

@Suppress("UNREACHABLE_CODE", "DEPRECATION")
class LaporFragment : Fragment(), LifecycleObserver {

    private lateinit var binding: FragmentLaporBinding
    private lateinit var progresBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentLaporBinding.inflate(layoutInflater)
        return binding.root



    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onCreated(){
        progresBar = binding.progresBar
        setUpListener()




        binding.button.setOnClickListener {
            if (isValidate()){
                progresBar.visibility = View.INVISIBLE
                submitData ()
            } else {
                Toast.makeText(context, "Error!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun submitData() {
        val username = binding.edtInputName.text.toString()
        val address = binding.edtInputAddress.text.toString()
        val report = binding.edtInputReport.text.toString()
        val phone = binding.edtInputNumberPhone.text.toString()
        val email = binding.edtInputEmail.text.toString()
        val gender = binding.edtInputJob.text.toString()
        val job = binding.edtInputJob.text.toString()

        val url = "https://x8ki-letl-twmt.n7.xano.io/api:iSXhPlyM/laporan"

        val jsonObjReq = object : StringRequest(Request.Method.POST, url,
        Response.Listener { response ->
            Toast.makeText(context, "Data Berhasil Di Input",Toast.LENGTH_SHORT).show()
            Log.d(ContentValues.TAG, "onResponse: $response")

            val i = Intent(this.context, MainActivity::class.java)
            startActivity(i)
        },
        Response.ErrorListener { error -> Log.d("tag","Error Response")}
        ){
            override fun getParams (): HashMap<String,String>{
                val map = HashMap<String,String>()
                map["name"] = username
                map["address"] = address
                map["whatsapp"] = phone
                map["email"] = email
                map["gender"] = gender
                map["profession"]= job
                map["content"] = report

                return map
            }
        }
        progresBar.visibility = View.VISIBLE
        jsonObjReq.retryPolicy = DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        Volley.newRequestQueue(this.context).add(jsonObjReq)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycle.addObserver(this)
//        onCreated()
    }

    override fun onDetach() {
        super.onDetach()
        lifecycle.removeObserver(this)
    }

    private fun isValidate(): Boolean = validateUserName() && validateAddress() && validateNumber() && validateEmail() && validateJob()
            && validateReport() && validateGender()

    private fun setUpListener() {
        binding.edtInputName.addTextChangedListener(TextFieldValidation(binding.edtInputName))
        binding.edtInputAddress.addTextChangedListener(TextFieldValidation(binding.edtInputAddress))
        binding.edtInputNumberPhone.addTextChangedListener(TextFieldValidation(binding.edtInputNumberPhone))
        binding.edtInputEmail.addTextChangedListener(TextFieldValidation(binding.edtInputEmail))
        binding.edtInputJob.addTextChangedListener(TextFieldValidation(binding.edtInputJob))
        binding.edtInputReport.addTextChangedListener(TextFieldValidation(binding.edtInputReport))
        binding.edtInputGender.addTextChangedListener(TextFieldValidation(binding.edtInputGender))

    }

    private fun validateUserName(): Boolean {


        val username = binding.edtInputName.text.toString()
        return when {
            username.isEmpty() -> {
                binding.edtLayoutName.error = "Username tidak boleh kosong"
                false
            }
            username.length > 20 ->{
                binding.edtLayoutName.error = "Karakter tidak boleh melebihi 20 karakter"
                false
            }
            else -> {
                binding.edtLayoutName.isErrorEnabled = false
                true
            }
        }
    }

    private fun validateAddress(): Boolean {
        val edtInputAddress = binding.edtInputAddress
        val edtLayoutAddresss = binding.edtLayoutAddress

        val address = edtInputAddress.text.toString().trim()
        return when {
            address.isEmpty() -> {
                edtLayoutAddresss.error = "Alamat tidak boleh kosong"
                edtLayoutAddresss.requestFocus()
                false
            }
            address.length > 250 -> {
                edtLayoutAddresss.error = "Alamat terlalu panjang"
                edtLayoutAddresss.requestFocus()
                false
            }
            else -> {
                edtLayoutAddresss.isErrorEnabled = false
                true
            }
        }
    }

    private fun validateNumber(): Boolean {
        val edtInputNumber = binding.edtInputNumberPhone
        val edtLayoutNumber = binding.edtLayoutNumberPhone

        val number = edtInputNumber.text.toString().trim()
        return when{
            number.isEmpty() -> {
                edtLayoutNumber.error = "Nomor HP tidak boleh kosong !"
                edtLayoutNumber.requestFocus()
                false
            }
            number.length > 13 -> {
                edtLayoutNumber.error = "Nomor HP tidak sesuai"
                edtLayoutNumber.requestFocus()
                false
            }
            else -> {
                edtLayoutNumber.isErrorEnabled = false
                true
            }
        }
    }

    private fun validateEmail () : Boolean {
        val layoutEmail = binding.edtLayoutEmail
        val editEmail = binding.edtInputEmail
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val email = editEmail.text.toString().trim()

        return when {
            email.isEmpty() -> {
                layoutEmail.error = "Email tidak boleh kosong"
                layoutEmail.requestFocus()
                false
            }

            else -> {
                if (email.matches(emailPattern.toRegex())){
                    layoutEmail.isErrorEnabled = false
                } else{
                    layoutEmail.error ="Email is invalid"
                }
                true
            }
        }
    }
    private fun validateGender(): Boolean {
        val layoutGender = binding.edtLayoutGender
        val editGender = binding.edtInputGender
        val genderPattern = "laki-laki"
        val genderPattern2 = "perempuan"

        val gender = editGender.text.toString().trim()

        return when{
            gender.isEmpty() -> {
                layoutGender.error = "Jenis kelamin tidak boleh kosong"
                layoutGender.requestFocus()
                false
            }
            gender.length > 9 -> {
                layoutGender.error = "Karakter terlalu panjang !"
                layoutGender.requestFocus()
                false
            }

            else -> {
                when {
                    gender.matches(genderPattern.toRegex()) -> {
                        layoutGender.isErrorEnabled = false
                    }
                    gender.matches(genderPattern2.toRegex()) -> {
                        layoutGender.isErrorEnabled = false
                    }
                    else -> {
                        layoutGender.error = "Gender tidak valid"
                    }
                }

                true
            }
        }


    }


    private fun validateJob (): Boolean {
        val jobLayout = binding.edtLayoutJob
        val jobInput = binding.edtInputJob

        val job = jobInput.text.toString().trim()

        return when {
            job.isEmpty() -> {
                jobLayout.error = "Pekerjaan tidak boleh kosong !"
                jobLayout.requestFocus()
                false
            }
            job.length >= 25 -> {
                jobLayout.error = "Terlalu panjang"
                jobLayout.requestFocus()
                false
            }
            else -> {
                jobLayout.isErrorEnabled = false
                true
            }
        }
    }

    private fun validateReport(): Boolean {
        val reportLayout = binding.edtLayoutReport
        val reportInput = binding.edtInputReport

        val report = reportInput.text.toString().trim()

        return when{
            report.isEmpty() -> {
                reportLayout.error = "Laporan tidak boleh kosong"
                reportLayout.requestFocus()
                false
            }
            report.length >= 1500 -> {
                reportLayout.error = "Laporan terlalu panjang"
                reportLayout.requestFocus()
                false
            }
            else -> {
                reportLayout.isErrorEnabled = false

                true
            }
        }
    }


    inner class TextFieldValidation (private val view: View) : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            when (view.id){
                R.id.edt_input_name -> {
                    validateUserName()
                }
            }

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                when (view.id){
                    R.id.edt_input_name -> {
                        validateUserName()
                    }
                    R.id.edt_input_address ->{
                        validateAddress()
                    }
                    R.id.edt_input_numberPhone ->{
                        validateNumber()
                    }
                    R.id.edt_input_email ->{
                        validateEmail()
                    }
                    R.id.edt_input_job -> {
                        validateJob()
                    }
                    R.id.edt_input_report -> {
                        validateReport()
                    }
                    R.id.edt_input_gender -> {
                        validateGender()
                    }
                }
        }

        override fun afterTextChanged(p0: Editable?) {
            when (view.id){
                R.id.edt_input_email -> {
                    validateEmail()
                }
            }
        }


    }






}


