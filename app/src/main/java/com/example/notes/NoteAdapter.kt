package com.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.data.DataService
import com.example.notes.models.NoteType

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var list = DataService.getAllNotes("")

    class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardView : CardView = view.findViewById(R.id.note_card)
        val textView : TextView = view.findViewById(R.id.note_list_text)
        val description : TextView = view.findViewById(R.id.note_description)
        val arrow : ImageView = view.findViewById(R.id.arrow_button)
        val hiddenView: ConstraintLayout = view.findViewById(R.id.hidden_view)
        val icon : ImageView = view.findViewById(R.id.note_list_icon)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.note_list_view, parent, false)
        return NoteViewHolder(layout)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = list[position]
        holder.textView.text = item.name
        holder.description.text = item.description

        when (item.type) {
            NoteType.GOOD -> holder.icon.setImageResource(R.drawable.ic_good)
            NoteType.BAD -> holder.icon.setImageResource(R.drawable.ic_bad)
            NoteType.WARNING -> holder.icon.setImageResource(R.drawable.ic_warn)
        }

        holder.cardView.setOnClickListener {
            // If view is currently visible, make invisible
            if (holder.hiddenView.visibility == View.VISIBLE) {
                holder.hiddenView.visibility = View.GONE
                holder.arrow.setImageResource(R.drawable.ic_expand_more)
            } else {
                // View currently hidden, so make visible
                holder.hiddenView.visibility = View.VISIBLE
                holder.arrow.setImageResource(R.drawable.ic_expand_less)
            }
        }

        // TODO
//        holder.button.setOnClickListener {
//            val action = LetterListFragmentDirections.actionLetterListFragmentToWordListFragment(letter = holder.button.text.toString())
//            holder.view.findNavController().navigate(action)
//        }
    }

    fun onSearchChange(value: String) {
        list = DataService.getAllNotes(value)
        notifyDataSetChanged()
    }



}