package walter.com.tottustest.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_information.view.*
import walter.com.tottustest.R
import walter.com.tottustest.util.withArgs

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
class AddBottomSheetFragment: BottomSheetDialogFragment() {

    companion object {

        const val FIELD_1 = "field1"
        const val FIELD_2 = "field2"

        fun newInstance(field1: String, field2: String) = AddBottomSheetFragment().withArgs {
            putString(FIELD_1, field1)
            putString(FIELD_2, field2)
        }

    }

    private var hint1 = ""
    private var hint2 = ""
    var onAgree: (String, String) -> Unit = { _, _ -> }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let {
            hint1 = it.getString(FIELD_1) as String
            hint2 = it.getString(FIELD_2) as String
        }

        return inflater.inflate(R.layout.fragment_add_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.txtField1.hint = hint1
        view.txtField2.hint = hint2

        view.btnAgree.setOnClickListener {
            onClick(view.txtField1, view.txtField2)
        }
    }

    private fun onClick(text1: EditText, text2: EditText) {
        if (text1.text.isEmpty()) {
            text1.error = "Este campo es necesario"
            return
        }
        if (text2.text.isEmpty()) {
            text2.error = "Este campo es necesario"
            return
        }
        dismiss()
        onAgree(text1.text.toString(), text2.text.toString())
    }

}