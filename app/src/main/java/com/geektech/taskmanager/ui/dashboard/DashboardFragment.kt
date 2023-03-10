package com.geektech.taskmanager.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.geektech.taskmanager.R
import com.geektech.taskmanager.databinding.FragmentDashboardBinding
import com.geektech.taskmanager.model.Quote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var db: FirebaseFirestore

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()

        binding.btnSave.setOnClickListener {
            if (binding.etAuthor.text?.isNotEmpty()!! && binding.etQuote.text?.isNotEmpty()!!) {
                save()
            } else if (binding.etAuthor.text!!.isEmpty()) {
                binding.etAuthor.error = getString(R.string.error_empty_value)
            } else if (binding.etQuote.text!!.isEmpty()) {
                binding.etQuote.error = getString(R.string.error_empty_value)
            } else {
                binding.etAuthor.error = getString(R.string.error_empty_value)
                binding.etQuote.error = getString(R.string.error_empty_value)
            }
        }
    }

    private fun save() {
        val quote = Quote(
            text = binding.etQuote.text.toString(),
            author = binding.etAuthor.text.toString()
        )
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString()).add(quote)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Безумно можно быть первым!!!", Toast.LENGTH_SHORT)
                    .show()
                binding.etQuote.text?.clear()
                binding.etAuthor.text?.clear()
            }.addOnFailureListener {
            Log.e("di", "save error: " + it.message)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}