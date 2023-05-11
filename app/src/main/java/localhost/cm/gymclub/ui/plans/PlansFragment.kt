package localhost.cm.gymclub.ui.plans

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import localhost.cm.gymclub.R
import localhost.cm.gymclub.databinding.ActivityLoginBinding

@AndroidEntryPoint
class PlansFragment : Fragment() {
    private val viewModel: PlansViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_plans, container, false)
    }

    override fun onResume() {
        super.onResume()
        val view = requireView()
        val recycler = view.findViewById<RecyclerView>(R.id.fragment_recycler_view)
        val usernameTextView = view.findViewById<TextView>(R.id.usernameTextView)
        val newPlanButton = view.findViewById<MaterialButton>(R.id.add_workout)


        newPlanButton.setOnClickListener {
            val action = PlansFragmentDirections.actionPlansFragmentToAddPlanFragment()
            view.findNavController().navigate(action)
        }

        // get data
        viewModel.getData()
        viewModel.plans.observe(viewLifecycleOwner) {
            recycler.apply {
                adapter = PlansAdapter(it)
                layoutManager = LinearLayoutManager(context)
            }
        }
        viewModel.user.observe(viewLifecycleOwner) {
            usernameTextView.text = it.fullName
        }
    }

}