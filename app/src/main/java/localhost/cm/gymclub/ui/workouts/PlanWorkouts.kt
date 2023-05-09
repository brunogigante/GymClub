package localhost.cm.gymclub.ui.workouts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import localhost.cm.gymclub.R

@AndroidEntryPoint
class PlanWorkouts : Fragment() {
    val viewModel: PlanWorkoutsViewModel by viewModels()
    val args: PlanWorkoutsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_plan_workouts, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recycler = requireView().findViewById<RecyclerView>(R.id.fragment_recycler_view)
        viewModel.getWorkoutsForPlanId(args.planId)

        viewModel.workouts.observe(viewLifecycleOwner) {
            recycler.apply {
                adapter = PlansWorkoutsAdapter(it)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

}