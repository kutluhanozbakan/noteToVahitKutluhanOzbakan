package ise308.ozbakan.vahikutluhan.notetovahitkutluhanzbakan

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.*
import java.lang.StringBuilder

class JSONSerializer(private val filename : String, private val context: Context) {
    //We define a jsonArray to store informations
    //And also we store the information in jsonArray with Writer
    @Throws(IOException::class, JSONException::class)
    fun save(noteList: List<Note>) {
        val jsonArray = JSONArray()
        for (note in noteList) {
            jsonArray.put(note.convertTOJSON())
        }
        var writer: Writer? = null
        try {
            val outFile = context.openFileOutput(filename, Context.MODE_PRIVATE)
            writer = OutputStreamWriter(outFile)
            writer.write(jsonArray.toString())
        } finally {
            if (writer != null) {
                writer.close()
            }
        }

    }
    //this function is load the current json folder and show the data.
    @Throws(IOException::class, JSONException::class)
    fun load(): ArrayList<Note> {
        val noteList = ArrayList<Note>()
        var reader: BufferedReader? = null

        try {
            val inFile = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(inFile))
            val jsonString = StringBuilder()

            for (line in reader.readLine()) {
                jsonString.append(line)
            }

            val jsonArray = JSONTokener(jsonString.toString())
                .nextValue() as JSONArray
            for (i in 0 until jsonArray.length()) {
                noteList.add(Note(jsonArray.getJSONObject(i)))
            }

        } catch (e: FileNotFoundException) {
            //we will ignore this one, since it happens
            // when we start fresh. Yo
        } finally {
            reader!!.close()
        }
        return noteList
    }
}