package walter.com.tottustest.ui.main.team.detail

import android.view.View
import kotlinx.android.synthetic.main.layout_member.view.*
import walter.com.tottustest.R
import walter.com.tottustest.infraestructure.base.BaseRecycler
import walter.com.tottustest.model.entity.Member

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
class MemberAdapter: BaseRecycler<Member>() {

    override fun getLayout(): Int = R.layout.layout_member

    override fun onBindViewHold(position: Int, itemView: View) {
        with(list[position]) {
            itemView.txtName.text = name
            itemView.txtPhrase.text = email
        }

        itemView.btnDelete.setOnClickListener { itemClickListener.onClick(
            position, list[position], it
        ) }
    }

    override fun onClick(itemView: View, position: Int, data: Member) {

    }
}