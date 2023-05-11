package localhost.cm.gymclub.ui.workouts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import localhost.cm.gymclub.R
import localhost.cm.gymclub.data.entity.response.WorkoutResponse
import org.w3c.dom.Text

class PlansWorkoutsAdapter(private val workouts: List<WorkoutResponse>): RecyclerView.Adapter<PlansWorkoutsAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val planTextView: TextView
        val workoutTextView: TextView
        val workoutDescriptionTextView: TextView

        init {
            planTextView = view.findViewById(R.id.textViewPlanName)
            workoutTextView = view.findViewById(R.id.textViewWorKoutName)
            workoutDescriptionTextView = view.findViewById(R.id.textViewDescription)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.workout_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = workouts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = workouts[position]

        holder.planTextView.text = workout.name
        holder.workoutTextView.text = workout.plan.name
        holder.workoutDescriptionTextView.text = workout.description
        holder.itemView.setOnClickListener {
            val action =
                PlanWorkoutsDirections.actionPlanWorkoutFragmentToPlanWorkoutsExercises(workout.id.toInt())
            val navController = holder.itemView.findNavController()
            navController.navigate(action)
        }
    }
}