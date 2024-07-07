package com.example.animalquizgame.utlis.uiUtils.tables

import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class CostumizeTable {
    fun Costumize(tableRow: TableRow, vararg textViews: TextView){
        tableRow.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        for(tV in textViews){
            tV.layoutParams = TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f
            )
        }

    }
}