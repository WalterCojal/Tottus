package walter.com.tottustest.ui.main.team.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_detail_team.view.*
import walter.com.tottustest.R
import walter.com.tottustest.model.entity.Member
import walter.com.tottustest.model.entity.Team
import walter.com.tottustest.ui.dialog.AddBottomSheetFragment
import walter.com.tottustest.ui.main.HomeActivity

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
class DetailTeamFragment: Fragment() {

    private val adapter = MemberAdapter()
    private var teamId = 0
    private var title = ""
    private lateinit var viewModel: MemberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            teamId = it.getInt("teamId")
            title = it.getString("title", "")
        }
        viewModel = ViewModelProvider(this)[MemberViewModel::class.java]
        return inflater.inflate(R.layout.fragment_detail_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.rvMembers.layoutManager = LinearLayoutManager(requireContext())
        view.rvMembers.adapter = adapter

        (activity as HomeActivity).supportActionBar?.title = title

        adapter.setOnClickListener { i, member, view ->
            viewModel.delete(member)
        }

        viewModel.onMessageLiveData().observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.membersObserver.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

        viewModel.getMembers(teamId)

    }

    private fun addMemberDialog() {
        val dialog = AddBottomSheetFragment.newInstance("Nombre", "Correo").apply {
            onAgree = { name, email ->
                viewModel.addMember(Member(0, teamId, name, email))
            }
        }
        dialog.show(childFragmentManager, "AddBottomSheetFragment")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_more) {
            addMemberDialog()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}