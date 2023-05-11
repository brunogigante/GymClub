package localhost.cm.gymclub.ui.workouts

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import localhost.cm.gymclub.R

@AndroidEntryPoint
class PlanWorkouts : Fragment() {
    private val viewModel: PlanWorkoutsViewModel by viewModels()
    private val args: PlanWorkoutsArgs by navArgs()

    private var planId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_plan_workouts, container, false)
    }

    override fun onResume() {
        super.onResume()

        val view = requireView()
        val recycler = view.findViewById<RecyclerView>(R.id.fragment_recycler_view)
        val planNameTextView = view.findViewById<TextView>(R.id.plan_name)
        val deleteButton = view.findViewById<TextView>(R.id.delete_workout)
        val addButton = view.findViewById<Button>(R.id.add_workout)

        viewModel.getWorkoutsAndPlanByPlanId(args.planId)

        viewModel.workouts.observe(viewLifecycleOwner) {
            recycler.apply {
                adapter = PlansWorkoutsAdapter(it)
                layoutManager = LinearLayoutManager(context)
            }
        }

        viewModel.plan.observe(viewLifecycleOwner) {
            planNameTextView.text = it.name
            planId = it.id
        }

        deleteButton.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle(R.string.delete_plan_confirmation)
                .setPositiveButton(R.string.yes) { _, _ ->
                    planId?.toInt()?.let { it1 -> viewModel.deletePlanByPlanId(it1) }
                    findNavController().popBackStack()
                }
                .setNegativeButton(R.string.no) { _, _ -> }.show()
        }

        addButton.setOnClickListener {
            val action = planId?.let { it1 ->
                PlanWorkoutsDirections.actionPlanWorkoutFragmentToAddWorkoutFragment(
                    it1.toInt())
            }
            if (action != null) {
                view.findNavController().navigate(action)
            }
        }
    }

}