package localhost.cm.gymclub.ui.exercises

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import localhost.cm.gymclub.R

@AndroidEntryPoint
class ExercisesFragment : Fragment() {
    private val viewModel: ExercisesViewModel by viewModels()
    private val args: ExercisesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_plan_workout_exercises, container, false)
    }

    override fun onResume() {
        super.onResume()

        val view = requireView()
        val recycler = view.findViewById<RecyclerView>(R.id.fragment_recycler_view)
        val workoutExercise = view.findViewById<TextView>(R.id.workoutName)
        val addButton = view.findViewById<Button>(R.id.addExercise)

        viewModel.getWorkoutExercisesByWorkoutId(args.workoutId)

        viewModel.workoutExercises.observe(viewLifecycleOwner) {
            recycler.apply {
                adapter = ExercisesAdapter(it)
                layoutManager = LinearLayoutManager(context)
            }
        }

        viewModel.workout.observe(viewLifecycleOwner) {
            workoutExercise.text = it.name

        }
        addButton.setOnClickListener {
            val action = ExercisesFragmentDirections.actionPlanWorkoutsExercisesToAddWorkoutExerciseFragment(args.workoutId)
            view.findNavController().navigate(action)
        }
    }
}
