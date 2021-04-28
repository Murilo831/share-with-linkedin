package com.example.myapplication2


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        shareLinkedin()
    }

    private fun shareLinkedin() {

        val btnShare = findViewById<Button>(R.id.btn_share_linkedin)
        btnShare?.setOnClickListener {

            val nomeProduto = binding.editNomeProduto.text.toString()
            val precoProduto = binding.editProductPrice.text.toString()
            val nomeMembro = binding.editMemberName.text.toString()
            val linkAppMatchfood = Uri.parse("https://play.google.com/store/apps/details?id=com.matchfood.app")
            val linkSiteMatchfood = Uri.parse("https://matchfood.com/baixe_agora")


            val text: String = "$nomeProduto "+"por apenas"+" R$$precoProduto? \n\n" +
                    "Você só encontra na nossa loja $nomeMembro "+"no APP"+" Matchfood " +
                    "\uD83D\uDE03 \n"+ //emoji
                    "linkAppMatchfood \n\n" +
                    "Aceitamos várias formas de pagamento! \uD83D\uDCB3 \n\n" + //emoji
                    "Baixe o app Matchfood e faça seu pedido: \nlinkSiteMatchfood"

            //com.example.sharefacebook/ -> No AndroidManifest
            val shareImage = Uri.parse("android.resource://com.example.sharefacebook/" + R.drawable.image)

            createLinkedinIntent(shareImage, text)
        }
    }

    private fun createLinkedinIntent(uri: Uri?, text: String?){


        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "*/*" // Funciona no Linkedin
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)

        shareIntent.setPackage("com.linkedin.android")
        //shareIntent.setPackage("com.google.android.gm")
        startActivity(shareIntent) //-- Antigo
        //startActivity(Intent.createChooser(shareIntent, "Share to")) // Mais opções de compartilhamento
    }


}