package com.example.notes.data

import com.example.notes.models.Note

class DataService {

    companion object {
        fun getAllNotes(search: String): List<Note> {
            val list = mutableListOf<Note>()

            for(i in 0..20) {
                list.add(Note("Note $i", "Description $i"))
            }

            return list.filter { it.name.contains(search, true)}
        }
    }
}