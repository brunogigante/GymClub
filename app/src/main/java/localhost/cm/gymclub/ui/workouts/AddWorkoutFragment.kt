package localhost.cm.gymclub.ui.workouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import localhost.cm.gymclub.R
import localhost.cm.gymclub.data.entity.response.ExerciseResponse

private const val PLAN_ID_ARG = "planId"

@AndroidEntryPoint
class AddWorkoutFragment : Fragment() {
    private val viewModel: AddWorkoutFragmentViewModel by viewModels()

    private var planId: Int? = null
    private var exercises: List<ExerciseResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            planId = it.getInt(PLAN_ID_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_workout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val view = requireView()

        val workoutNameTextView = view.findViewById<EditText>(R.id.Name)
        val workoutDescription = view.findViewById<EditText>(R.id.descricao)
        val addButton = view.findViewById<Button>(R.id.addExerciseButton)
        addButton.setOnClickListener {
            planId?.let { it1 ->
                viewModel.createWork(
                    it1,
                    workoutNameTextView.text.toString(),
                    workoutDescription.text.toString()
                )
            }
            Toast.makeText(context, "Successfully created workout", Toast.LENGTH_LONG).show()
            view.findNavController().popBackStack()
        }
    }
}