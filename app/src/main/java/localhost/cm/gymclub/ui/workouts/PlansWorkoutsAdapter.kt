package localhost.cm.gymclub.ui.workouts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import localhost.cm.gymclub.R
import localhost.cm.gymclub.data.entity.response.WorkoutResponse

class PlansWorkoutsAdapter(private val workouts: List<WorkoutResponse>): RecyclerView.Adapter<PlansWorkoutsAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val planTextView: TextView

        init {
            planTextView = view.findViewById(R.id.planName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.plan_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = workouts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = workouts[position]

        holder.planTextView.text = workout.name
    }
}