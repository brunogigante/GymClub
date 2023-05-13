package localhost.cm.gymclub.ui.sets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import localhost.cm.gymclub.R
import localhost.cm.gymclub.data.entity.response.SetResponse

class SetsAdapter(private val exerciseSets: List<SetResponse>): RecyclerView.Adapter<SetsAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val setsNameTextView: TextView
        val weightValue: TextView
        val repetitionsValue: TextView

        init {
            setsNameTextView = view.findViewById(R.id.setsNameTextView)
            weightValue = view.findViewById(R.id.weightValue)
            repetitionsValue = view.findViewById(R.id.repetitionsValue)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sets_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = exerciseSets.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = exerciseSets[position]

        holder.setsNameTextView.text = "Set #" + exercise.id.toString()
        holder.repetitionsValue.text = exercise.repetitions.toString()
        holder.weightValue.text = exercise.weight.toString() + " Kg"
    }
}