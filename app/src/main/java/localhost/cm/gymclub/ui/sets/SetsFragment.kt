package localhost.cm.gymclub.ui.sets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import localhost.cm.gymclub.R

@AndroidEntryPoint
class SetsFragment : Fragment() {
    private val viewModel: SetsViewModel by viewModels()
    private val args: SetsFragmentArgs by navArgs()
    private var setId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_sets, container, false)
    }

    override fun onResume() {
        super.onResume()

        val view = requireView()
        val recycler = view.findViewById<RecyclerView>(R.id.fragment_recycler_view)
        val setTexView = view.findViewById<TextView>(R.id.setsNameTextView)
        val setNumber = view.findViewById<TextView>(R.id.setsNameTextView)
        val addButton = view.findViewById<Button>(R.id.addSet)
        val lineChart = view.findViewById<LineChart>(R.id.line_chart)


        viewModel.getSetsFromExercises(args.workoutId, args.exerciseId)

        viewModel.workoutExercisesSets.observe(viewLifecycleOwner) {
            recycler.apply {
                adapter = SetsAdapter(it)
                layoutManager = LinearLayoutManager(context)
            }

            // chart
            val entries = mutableListOf<Entry>()
            for (set in it) {
                entries.add(Entry(set.weight.toFloat(), set.repetitions.toFloat()))
            }

            if (entries.size > 0) {
                val dataset = LineDataSet(entries, "Label")
                lineChart.data = LineData(dataset)
            }


        }


        addButton.setOnClickListener {
            val action =
                SetsFragmentDirections.actionSetsFragmentToAddSet(args.workoutId, args.exerciseId)
            view.findNavController().navigate(action)
        }
    }
}