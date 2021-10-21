package com.adhimbagas.finalprojectskripsi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.adhimbagas.finalprojectskripsi.R
import com.adhimbagas.finalprojectskripsi.databinding.FragmentLaporBinding
import com.google.android.material.textfield.TextInputLayout

@Suppress("UNREACHABLE_CODE")
class LaporFragment : Fragment() {

    private lateinit var binding: FragmentLaporBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment

        binding = FragmentLaporBinding.inflate(layoutInflater)
        return binding.root

        val edtInputName = binding.edtInputName
        val edtInputAddress = binding.edtInputAddress
        val edtLayoutName = binding.edtLayoutName
        val edtLayoutAddress = binding.edtLayoutAddress



        }



    //edit layout
    }

