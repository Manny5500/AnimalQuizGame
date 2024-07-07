package com.example.animalquizgame.utlis.uiUtils

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.animalquizgame.utlis.uiUtils.tables.ClearTable
import com.example.animalquizgame.utlis.uiUtils.tables.CostumizeTable
import com.example.animalquizgame.utlis.uiUtils.textViews.CostumizeTextView

class TableSetter (
    val context: Context,
    val tableLayout:TableLayout,
    val scoreList:ArrayList<Int>,
    val typeface: Typeface
    ) {
    init{
    }
    fun generateTable(maxRows:Int){
        val clearTable = ClearTable()
        clearTable.clearTable(tableLayout)

        val size = minOf(scoreList.size, maxRows)
        for (i in 0 until size step 2) {
            val tableRow = TableRow(context)
            setRow(i, tableRow)
            tableLayout.addView(tableRow)
        }
    }

    fun setRow(i:Int, tableRow: TableRow){

        for (j in i until i+2){
            val cellTextView = TextView(context)

            val costumizeTable = CostumizeTable()
            costumizeTable.Costumize(tableRow,cellTextView)
            val str = "${j+1}. ${scoreList[j]}"
            if(scoreList[j]!=0){
                cellTextView.text = str
            }
            if(j%2==0){
                cellTextView.gravity = Gravity.START
            }else{
                cellTextView.gravity = Gravity.END
            }

            val costumizeTextView = CostumizeTextView()
            costumizeTextView.setTextSizeAndPaddingForTextViews(
                22f, 16, typeface, cellTextView
            )
            tableRow.addView(cellTextView)
        }

    }
}