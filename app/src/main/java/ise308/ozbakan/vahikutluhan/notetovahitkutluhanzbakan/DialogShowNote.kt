package ise308.ozbakan.vahikutluhan.notetovahitkutluhanzbakan

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_show_note.*

class DialogShowNote : DialogFragment() {

    private  var note: Note? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //Data pulled using id to show data
        val builder = AlertDialog.Builder(this.activity!!)
        val inflater = activity!!.layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_show_note, null)
        val textViewtitle = dialogLayout.findViewById<TextView>(R.id.textTitle)
        val textViewDescription = dialogLayout.findViewById<TextView>(R.id.textDescription)
        val textViewIdea = dialogLayout.findViewById<TextView>(R.id.textViewIdea)
        val textViewToDo = dialogLayout.findViewById<TextView>(R.id.textViewTodo)
        val textViewImportant = dialogLayout.findViewById<TextView>(R.id.textViewImportant)
        val buttonDone = dialogLayout.findViewById<Button>(R.id.buttonOk)

        textViewtitle.text = note!!.title
        textViewDescription.text = note!!.description
        if (!note!!.important)
        {
            textViewImportant.visibility = View.GONE
        }
        if (!note!!.todo)
        {
            textViewToDo.visibility = View.GONE
        }
        if (!note!!.idea)
        {
            textViewIdea.visibility = View.GONE
        }

        buttonDone.setOnClickListener {
            dismiss()
        }
        //We define de message to show
        builder.setView(dialogLayout).setMessage(
            resources.getString(R.string.your_note))

        return builder.create()
    }

    fun sendNoteSelected(noteselected: Note)
    {
        note = noteselected
    }


}