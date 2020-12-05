package ise308.ozbakan.vahikutluhan.notetovahitkutluhanzbakan

import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var noteList : ArrayList<Note>? = null
    private var jsonSerializer: JSONSerializer? = null

    private var recyclerView: RecyclerView? = null
    private var adapter: NoteAdapter? = null
    private var showDividers: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        floatingActionButton3.setOnClickListener { view ->
            val dialog = DialogNewNote()
            dialog.show(supportFragmentManager, "124")
        }

        jsonSerializer = JSONSerializer("Note to Vahit Kutluhan Özbakan", applicationContext)
        try
        {
            noteList = jsonSerializer!!.load()
        }catch (e: Exception)
        {
            noteList = ArrayList()
        }

        recyclerView = findViewById<View>(R.id.recyclerView)
                    as RecyclerView

        adapter = NoteAdapter(this, noteList!!)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView!!.adapter = adapter
    }
    override fun onResume()
    {
        super.onResume()
        val prefs = getSharedPreferences("Note to Vahit Kutluhan Özbakan", Context.MODE_PRIVATE)
        showDividers = prefs.getBoolean("dividers",true)
        if (showDividers)
        {
            recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        }else
        {
            if (recyclerView!!.itemDecorationCount > 0)
                recyclerView!!.removeItemDecorationAt(0)
        }
    }


    fun createNewNote(n: Note)
    {
        noteList!!.add(n)
        adapter!!.notifyDataSetChanged()

    }

    fun showNote(noteToShow: Int) {
        val dialog = DialogShowNote()
        noteList?.get(noteToShow)?.let { dialog.sendNoteSelected(it) }
        dialog.show(supportFragmentManager, "")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val b = when(id)
        {
                R.id.settings -> 
                {
                    val intentToSettings = Intent(this,SettingActivity::class.java)
                    startActivity(intentToSettings)
                    true
                }
                else -> super.onOptionsItemSelected(item)
        }
        return b
    }

        private fun saveNotes()
        {
            try {
                jsonSerializer!!.save(this.noteList!!)
            }catch (e: Exception)
            {
                Log.i(TAG, "saveNotes: Error loading notes")
            }

        }
    override fun onPause()
    {
        super.onPause()
        saveNotes()
    }

}