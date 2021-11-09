package com.example.notes.data

import com.example.notes.models.Note
import com.example.notes.models.NoteType

class DataService {

    companion object {
        fun getAllNotes(search: String): List<Note> {
            val list = mutableListOf<Note>()

            for(i in 0..20) {
                list.add(Note("Note $i", "Description $i", getRandomType()))
            }

            return list.filter { it.name.contains(search, true)}
        }

        private fun getRandomType(): NoteType {
            val types = listOf(NoteType.GOOD, NoteType.BAD, NoteType.WARNING)
            return types.shuffled().first()
        }
    }
}