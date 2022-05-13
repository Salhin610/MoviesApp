package com.salhin.moviesapp

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.LinearLayout


var progressDialog: Dialog? = null

var mContext: Context? = null


fun showProgressDialog(context: Context) {
    if (context !== mContext) {

        progressDialog = Dialog(context)
    }
    mContext = context


    progressDialog?.setContentView(R.layout.progress_bar)
    val window = progressDialog!!.window
    window!!.setLayout(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT
    )
    progressDialog!!.setCancelable(false)
    progressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    progressDialog!!.show()
}

fun hideProgressDialog() {
    if (progressDialog != null)
        progressDialog!!.dismiss()
}