package com.example.animalquizgame.utlis.uiUtils.tables

import android.widget.TableLayout
import android.widget.TableRow

class ClearTable {
    fun clearTable(tableLayout: TableLayout){
        var rowCount = tableLayout.childCount
        for (i in 1 until rowCount) {
            val child = tableLayout.getChildAt(1)
            if (child is TableRow) {
                // Remove the TableRow from the TableLayout
                tableLayout.removeViewAt(1)
            }
        }

    }
}