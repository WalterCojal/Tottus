package walter.com.tottustest.infraestructure.base

import android.view.View

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
interface ItemClickListener<T> {
    fun onClick(position: Int, data: T, view: View)
}