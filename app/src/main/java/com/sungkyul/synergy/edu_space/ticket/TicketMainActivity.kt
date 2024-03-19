package com.sungkyul.synergy.edu_space.ticket
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sungkyul.synergy.R
import com.sungkyul.synergy.edu_space.kakaotaxi.mapActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class TicketMainActivity : AppCompatActivity() {

    private var dateTextView: TextView? = null
    private var timeTextView:android.widget.TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_main)

        val resButton: Button = findViewById(R.id.button7)

        resButton.setOnClickListener {
            val mapIntent = Intent(this, Ticket2MainActivity::class.java)
            startActivity(mapIntent)
        }



    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.ticket_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return super.onOptionsItemSelected(item)
    }
}