package com.example.hesapmakinesiuygulama

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hesapmakinesiuygulama.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
    fun sayisec(sayisecmeislemi: View) {
        if (yenioperator) {
            // Eğer yeni bir işlem başlatıldıysa, sonucu temizle
            binding.sonuc.text = ""
            yenioperator = false // Yeni sayıya geçildi
        }

        var butonsec = sayisecmeislemi as Button
        val sonuc = binding.sonuc // burada sonuc TextView'i bağlanıyor
        var butondeger: String = sonuc.text.toString()

        when (butonsec.id) {
            binding.sifir.id -> { butondeger += "0" }
            binding.bir.id -> { butondeger += "1" }
            binding.iki.id -> { butondeger += "2" }
            binding.uc.id -> { butondeger += "3" }
            binding.dort.id -> { butondeger += "4" }
            binding.bes.id -> { butondeger += "5" }
            binding.alti.id -> { butondeger += "6" }
            binding.yedi.id -> { butondeger += "7" }
            binding.sekiz.id -> { butondeger += "8" }
            binding.dokuz.id -> { butondeger += "9" }
        }
        sonuc.text = butondeger
    }


    var operator= ""
    var eskisayi = ""
    var yenioperator = true

    fun islem(islemsecmeislemi: View) {
        var islemsec = islemsecmeislemi as Button
        // Operatörü belirle
        when (islemsec.id) {
            binding.carp.id -> { operator = "*" }
            binding.bol.id -> { operator = "/" }
            binding.topla.id -> { operator = "+" }
            binding.cikar.id -> { operator = "-" }
        }

        eskisayi = binding.sonuc.text.toString()
        yenioperator = true // Yeni işlem için bayrağı ayarla
    }

    fun esittir(esittir: View) {
        // Eğer yeni işlem başlatılmışsa, eski sayıyı ve operatörü güncellemeyin
        if (yenioperator) {
            return // Hiçbir işlem yapma
        }

        var yenisayi = binding.sonuc.text.toString()
        if (eskisayi.isEmpty() || yenisayi.isEmpty()) {
            // Hata mesajı veya bir şeyler yapabilirsiniz
            return
        }

        var islemsonucu: Double? = null
        when (operator) {
            "*" -> { islemsonucu = eskisayi.toDouble() * yenisayi.toDouble() }
            "/" -> {
                if (yenisayi.toDouble() != 0.0) {
                    islemsonucu = eskisayi.toDouble() / yenisayi.toDouble()
                } else {
                    // Sıfıra bölme hatası
                    return
                }
            }
            "+" -> { islemsonucu = eskisayi.toDouble() + yenisayi.toDouble() }
            "-" -> { islemsonucu = eskisayi.toDouble() - yenisayi.toDouble() }
        }

        // Sonucu TextView'e yaz
        binding.sonuc.text = islemsonucu.toString()

        // İşlem tamamlandığında yeni işlem için hazır olun
        eskisayi = islemsonucu.toString() // Sonucu eski sayıya atayın
        yenioperator = true // Yeni işlem için bayrağı ayarla
    }



    fun ac(silmeislemi : View){
        binding.sonuc.text = "0"
        yenioperator = true
    }


}