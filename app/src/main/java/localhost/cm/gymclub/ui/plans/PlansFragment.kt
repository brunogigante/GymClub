package localhost.cm.gymclub.ui.plans

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recycler = requireView().findViewById<RecyclerView>(R.id.fragment_recycler_view)

        viewModel.plans.observe(viewLifecycleOwner) {
            recycler.apply {
                adapter = PlansAdapter(it)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

}