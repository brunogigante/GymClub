package localhost.cm.gymclub.ui.exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import localhost.cm.gymclub.R
import localhost.cm.gymclub.data.entity.response.ExerciseResponse
import org.w3c.dom.Text

class ExercisesAdapter(private val exerciseWorkouts: List<ExerciseResponse>): RecyclerView.Adapter<ExercisesAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val exerciseNameTextView: TextView
        val categoryTextView: TextView
        val descriptionTextView: TextView
        val weightValue: TextView
        val repetitionsValue: TextView

        init {
            exerciseNameTextView = view.findViewById(R.id.exerciseNameTextView)
            categoryTextView = view.findViewById(R.id.categoryTextView)
            descriptionTextView = view.findViewById(R.id.textViewDescription)
            weightValue = view.findViewById(R.id.weightValue)
            repetitionsValue = view.findViewById(R.id.repetitionsValue)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = exerciseWorkouts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = exerciseWorkouts[position]

        holder.exerciseNameTextView.text = exercise.name
        holder.categoryTextView.text = exercise.category
        holder.descriptionTextView.text = exercise.description
        holder.repetitionsValue.text = exercise.repetitions.toString()
        holder.weightValue.text = exercise.weight.toString() + " Kg"
    }
}