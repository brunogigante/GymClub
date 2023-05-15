package localhost.cm.gymclub.ui.exercises

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import localhost.cm.gymclub.R
import localhost.cm.gymclub.ui.sets.AddSetsViewModel

private const val SET_ID_ARG = "setId"

@AndroidEntryPoint
class AddSetsFragment : Fragment() {
    private val viewModel: AddSetsViewModel by viewModels()
    private val args: AddExerciseFragmentArgs by navArgs()
    private var selectSetId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectSetId = it.getInt(SET_ID_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_add_sets, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val view = requireView()

        val reps = view.findViewById<EditText>(R.id.reps)
        val weight = view.findViewById<EditText>(R.id.weight)
        val addButton = view.findViewById<Button>(R.id.addExerciseButton)

        addButton.setOnClickListener {
/*            selectSetId?.let { it1 ->
                viewModel.addNewSetToExercise(
                    it1,
                    Integer.parseInt(reps.text.toString()),
                    Integer.parseInt(weight.text.toString())
                )*/
            }

            Toast.makeText(context, "Successfully created workout", Toast.LENGTH_LONG).show()
            view.findNavController().popBackStack()
        }
}