package localhost.cm.gymclub.ui.exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import localhost.cm.gymclub.R
import localhost.cm.gymclub.data.entity.response.ExerciseResponse

class ExercisesAdapter(private val exerciseWorkouts: List<ExerciseResponse>): RecyclerView.Adapter<ExercisesAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val workoutTextView: TextView
        val workoutDescriptionTextView: TextView

        init {
            workoutTextView = view.findViewById(R.id.textViewWorKoutName)
            workoutDescriptionTextView = view.findViewById(R.id.textViewDescription)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.workout_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = exerciseWorkouts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = exerciseWorkouts[position]

        holder.workoutTextView.text = exercise.name
        holder.workoutDescriptionTextView.text = exercise.description
    }
}