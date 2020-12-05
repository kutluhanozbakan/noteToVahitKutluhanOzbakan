package ise308.ozbakan.vahikutluhan.notetovahitkutluhanzbakan

import org.json.JSONException
import org.json.JSONObject

private val JSON_TITLE = "title"
private val JSON_DESCRIPTION = "description"
private val JSON_IDEA = "idea"
private val JSON_TODO = "todo"
private val JSON_IMPORTANT = "important"



class Note {
    constructor()
    {

    }
    var title: String? = null
    var description: String? = null
    var idea: Boolean= false
    var todo: Boolean= false
    var important: Boolean= false

    @Throws(JSONException::class)
    constructor(jsonObject : JSONObject)
    {
        title = jsonObject.getString(JSON_TITLE)
        description = jsonObject.getString(JSON_DESCRIPTION)
        idea = jsonObject.getBoolean(JSON_IDEA)
        todo= jsonObject.getBoolean(JSON_TODO)
        important=jsonObject.getBoolean(JSON_IMPORTANT)

    }

    @Throws(JSONException::class)
    fun convertTOJSON(): JSONObject
    {
        val jsonObject = JSONObject()
        jsonObject.put(JSON_TITLE, title)
        jsonObject.put(JSON_DESCRIPTION, description)
        jsonObject.put(JSON_IDEA, idea)
        jsonObject.put(JSON_TODO, todo)
        jsonObject.put(JSON_IMPORTANT, important)
        return jsonObject

    }


}