package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMyProfileBinding
import com.example.myapplication.ui.register.RegisterActivity
import java.sql.Connection
import java.sql.DriverManager

class MyProfileFragment : Fragment() {

    lateinit var connection: Connection
    lateinit var connectionResult: String

    companion object {
        fun newInstance() = MyProfileFragment()
    }

    private lateinit var viewModel: MyProfileViewModel
    private var _binding: FragmentMyProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyProfileViewModel::class.java).apply {
            setIndex(1)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Async().execute()
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        val root = binding.root
        val button: Button = root.findViewById(R.id.change_details)
        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, RegisterActivity::class.java)
            startActivity(intent)
        })
        val emailView: TextView = binding.email
        viewModel.text.observe(viewLifecycleOwner, Observer {
            emailView.text = "Email: yoan.panayotov1@gmail.com"
        })
        val weightView: TextView = binding.weight
        viewModel.text.observe(viewLifecycleOwner, Observer {
            weightView.text = "Weight: 86kg"
        })
        val heightView: TextView = binding.height
        viewModel.text.observe(viewLifecycleOwner, Observer {
            heightView.text = "Height: 183cm"
        })
        val dateOfBirthView: TextView = binding.dateOfBirth
        viewModel.text.observe(viewLifecycleOwner, Observer {
            dateOfBirthView.text = "Date Of Birth: 25/02/2002"
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

    class Async : AsyncTask<Void, Void, Void>() {
        var records: String = ""
        var error: String = ""
        override fun doInBackground(vararg p0: Void?): Void? {
            try {
                Class.forName("com.mysql.jdbc.Driver")

                val jdbcUrl = "jdbc:mysql://192.168.0.111:3306/test"
                println("DARK MAGIC !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                val connection = DriverManager.getConnection(
                    jdbcUrl, "kris", "qwerty")
                println("DARK ------------")
                println(connection.isValid(0))
                println("DARK ------------")

            } catch (e: Exception) {
                error = e.toString()
            }

            return null

        }
    }
}