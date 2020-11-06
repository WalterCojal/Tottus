package walter.com.tottustest.ui.main.team

import android.view.View
import kotlinx.android.synthetic.main.layout_team.view.*
import walter.com.tottustest.R
import walter.com.tottustest.infraestructure.base.BaseRecycler
import walter.com.tottustest.model.entity.Team

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
class TeamAdapter: BaseRecycler<Team>() {

    override fun getLayout(): Int = R.layout.layout_team

    override fun onBindViewHold(position: Int, itemView: View) {
        with(list[position]) {
            itemView.txtName.text = name
            itemView.txtPhrase.text = description
        }
    }

    override fun onClick(itemView: View, position: Int, data: Team) {
        itemClickListener.onClick(position, data, itemView)
    }

}