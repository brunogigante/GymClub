package localhost.cm.gymclub.ui.plans

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import localhost.cm.gymclub.R
import localhost.cm.gymclub.data.entity.response.TrainingPlanResponse

class PublicPlansAdapter(
    private val publicPlans: List<TrainingPlanResponse>,
    private val viewModel: PublicPlansViewModel,
) : RecyclerView.Adapter<PublicPlansAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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

    override fun getItemCount() = publicPlans.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plan = publicPlans[position]

        holder.planTextView.text = plan.name
        holder.itemView.setOnClickListener {
            AlertDialog.Builder(it.context)
                .setTitle(R.string.clone_plan_confirmation)
                .setPositiveButton(R.string.yes) { _, _ ->
                    viewModel.clonePlan(plan.id.toInt())
                }
                .setNegativeButton(R.string.no) { _, _ -> }.show()
        }
    }
}