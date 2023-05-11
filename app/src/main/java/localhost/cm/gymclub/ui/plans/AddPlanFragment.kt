package localhost.cm.gymclub.ui.plans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import localhost.cm.gymclub.R

@AndroidEntryPoint
class AddPlanFragment : Fragment() {
    private val viewModel: AddPlanViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_add_plan, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val view = requireView()

        val planNameEditText = view.findViewById<EditText>(R.id.editTextPlanName)
        val planVisibilityCheckBox = view.findViewById<CheckBox>(R.id.editTextPlanVisibility)


        val createPlanButton = view.findViewById<Button>(R.id.buttonCreatePlan)
        createPlanButton.setOnClickListener {
            viewModel.createPlan(planNameEditText.text.toString(), planVisibilityCheckBox.isChecked)

            Toast.makeText(context, "Success creating plan", Toast.LENGTH_LONG).show()
            view.findNavController().popBackStack()
        }
    }
}