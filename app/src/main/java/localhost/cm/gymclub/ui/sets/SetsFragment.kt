package localhost.cm.gymclub.ui.sets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import localhost.cm.gymclub.R
import localhost.cm.gymclub.ui.exercises.ExercisesAdapter
import localhost.cm.gymclub.ui.workouts.PlanWorkoutsDirections

@AndroidEntryPoint
class SetsFragment : Fragment(){
    private val viewModel: SetsViewModel by viewModels()
    private val args: SetsFragmentArgs by navArgs()
    private var setId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_sets, container, false)
    }
    override fun onResume() {
        super.onResume()

        val view = requireView()
        val recycler = view.findViewById<RecyclerView>(R.id.fragment_recycler_view)
        val setTexView = view.findViewById<TextView>(R.id.setsNameTextView)
        val setNumber = view.findViewById<TextView>(R.id.setsNameTextView)
        val addButton = view.findViewById<Button>(R.id.addSet)

        viewModel.getSetsFromExercises(args.workoutId, args.exerciseId)

        viewModel.workoutExercisesSets.observe(viewLifecycleOwner) {
            recycler.apply {
                adapter = SetsAdapter(it)
                layoutManager = LinearLayoutManager(context)
            }
        }


        addButton.setOnClickListener {
            val action = SetsFragmentDirections.actionSetsFragmentToAddSet(args.workoutId)
            view.findNavController().navigate(action)

        }
    }
}