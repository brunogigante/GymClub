package localhost.cm.gymclub.ui.exercises

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import localhost.cm.gymclub.R


@AndroidEntryPoint
class AddExerciseFragment : Fragment() {
    private val viewModel: AddExerciseViewModel by viewModels()
    private val args: AddExerciseFragmentArgs by navArgs()
    private var selectedExerciseId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_add_exercise, container, false)
    }

    override fun onResume() {
        super.onResume()
        val view = requireView()
        val addButton = view.findViewById<Button>(R.id.addExerciseButton)
        val spinner = view.findViewById<Spinner>(R.id.spinner)

        viewModel.getExercises()
        viewModel.exercises.observe(viewLifecycleOwner) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, it)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                   selectedExerciseId = it[position].id
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }

        addButton.setOnClickListener {
            Toast.makeText(context, "Successfully created workout", Toast.LENGTH_LONG).show()
            selectedExerciseId?.let { it1 -> viewModel.addNewWorkoutExercise(args.workoutId, it1) }

            Toast.makeText(context, "Exercise created successfully", Toast.LENGTH_LONG)
            view.findNavController().popBackStack()
        }
    }
}