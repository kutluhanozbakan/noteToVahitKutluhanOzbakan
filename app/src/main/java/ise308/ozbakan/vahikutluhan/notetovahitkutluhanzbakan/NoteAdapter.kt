package ise308.ozbakan.vahikutluhan.notetovahitkutluhanzbakan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val mainActivity: MainActivity, private val noteList: List<Note>) : RecyclerView.Adapter<NoteAdapter.ListItemHolder>()
{
    //We assign the data coming from xml to variables via class.
    inner class ListItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener
    {
        internal var title = view.findViewById<View>(R.id.textViewTitle) as TextView
        internal var description = view.findViewById<View>(R.id.textViewDescription) as TextView
        internal var status = view.findViewById<View>(R.id.textViewStatus) as TextView

        init
        {
            view.isClickable = true
            view.setOnClickListener(this)
        }
        //Clicking the button makes showNote active
        override fun onClick(view: View) {
            mainActivity.showNote(adapterPosition)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ListItemHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false)
        return ListItemHolder(itemView)
    }
    //keeps the number of incoming notes.
    override fun getItemCount(): Int
    {
        if (noteList != null) {
        return noteList.size
    }
        return -1

    }
    //While the notes are displayed in the XML part, we have determined how they appear.
    //For example shortening long descriptions
    override fun onBindViewHolder(holder: ListItemHolder, position: Int)
    {
        val note = noteList[position]
        holder.title.text = note.title
        val substringLength = if(note.description!!.length<15) note.description!!.length else 15
        val shortDesc = "${note.description!!.substring(0,substringLength)}..."
        holder.description.text = shortDesc

        // What is the status of the note?
        when {
            note.idea -> holder.status.text =
                mainActivity.resources.getString(R.string.idea_text)

            note.important -> holder.status.text =
                mainActivity.resources.getString(R.string.important_text)

            note.todo -> holder.status.text =
                mainActivity.resources.getString(R.string.todo_text)
        }

    }

}
