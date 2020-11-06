package walter.com.tottustest.ui.main.team

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_team.*
import walter.com.tottustest.R
import walter.com.tottustest.model.entity.Team
import walter.com.tottustest.ui.dialog.AddBottomSheetFragment

class TeamFragment : Fragment() {

    private lateinit var viewModel: TeamViewModel
    private val adapter = TeamAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTeams.layoutManager = LinearLayoutManager(requireContext())
        rvTeams.adapter = adapter

        adapter.setOnClickListener { i, team, view ->
            val bundle = Bundle().apply {
                putInt("teamId", team.id)
                putString("title", team.name)
            }
            Navigation.findNavController(view).navigate(R.id.action_nav_gallery_to_detailTeamFragment, bundle)
        }

        viewModel.teamsObserver.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

        viewModel.getAllTeams()

    }

    private fun addTeamDialog() {
        val dialog = AddBottomSheetFragment.newInstance("Nombre de equipo", "Frase de equipo").apply {
            onAgree = { name, phrase ->
                viewModel.addTeam(Team(0, name, phrase))
            }
        }
        dialog.show(childFragmentManager, "AddBottomSheetFragment")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_more) {
            addTeamDialog()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}