package localhost.cm.gymclub.ui.exercises

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import localhost.cm.gymclub.R
import localhost.cm.gymclub.data.entity.response.ExerciseResponse

class ExercisesAdapter(private val exerciseWorkouts: List<ExerciseResponse>, private val workoutId: Int): RecyclerView.Adapter<ExercisesAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val exerciseNameTextView: TextView
        val categoryTextView: TextView
        val descriptionTextView: TextView
        val weightValue: TextView
        val repetitionsValue: TextView
        val exerciseImage: ImageView


        init {
            exerciseNameTextView = view.findViewById(R.id.exerciseNameTextView)
            categoryTextView = view.findViewById(R.id.categoryTextView)
            descriptionTextView = view.findViewById(R.id.textViewDescription)
            weightValue = view.findViewById(R.id.weightValue)
            repetitionsValue = view.findViewById(R.id.repetitionsValue)
            exerciseImage = view.findViewById(R.id.imageView2)
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
        val categoryImageMap: Map<String, Int> = mapOf(
            "CHEST" to R.drawable.bench_press_ilustration,
            "BACK" to R.drawable.pulldown_ilustration,
            "LEGS" to R.drawable.barbell_squat_ilustration,
            /*"CALVES" to R.drawable.barbell_squat_ilustration,
            "SHOULDERS" to R.drawable.barbell_squat_ilustration,
            "BICEPS" to R.drawable.barbell_squat_ilustration,
            "TRICEPS" to R.drawable.barbell_squat_ilustration,
            "ABS" to R.drawable.barbell_squat_ilustration,*/
            // Add more mappings for other categories
        )
        holder.exerciseNameTextView.text = exercise.name
        holder.categoryTextView.text = exercise.category
        holder.descriptionTextView.text = exercise.description
        holder.repetitionsValue.text = exercise.repetitions.toString()
        holder.weightValue.text = exercise.weight.toString() + " Kg"

        val category = exercise.category
        val imageResId = categoryImageMap[category]
        imageResId?.let {
            holder.exerciseImage.setImageResource(it)
        }

        holder.itemView.setOnClickListener {
            val action =
                ExercisesFragmentDirections.actionPlanWorkoutsExercisesToSetsFragment(workoutId, exercise.id)
            val navController = holder.itemView.findNavController()
            navController.navigate(action)
        }
    }

}