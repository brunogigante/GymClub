package localhost.cm.gymclub.ui.plans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import localhost.cm.gymclub.R
import localhost.cm.gymclub.data.entity.response.TrainingPlanResponse

class PlansAdapter(private val plans: List<TrainingPlanResponse>): RecyclerView.Adapter<PlansAdapter.ViewHolder>() {
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

    override fun getItemCount() = plans.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plan = plans[position]

        holder.planTextView.text = plan.name
        holder.itemView.setOnClickListener {
            val action = PlansFragmentDirections.actionPlansFragmentToPlanWorkoutFragment(plan.id.toInt())
            val navController = holder.itemView.findNavController()
            navController.navigate(action)
        }
    }
}