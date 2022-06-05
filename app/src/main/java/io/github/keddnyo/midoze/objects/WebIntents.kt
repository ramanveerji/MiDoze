package io.github.keddnyo.midoze.objects

import android.content.Intent
import android.net.Uri

object WebIntents {

    val PROJECT = Intent(Intent.ACTION_VIEW,
        Uri.parse("https://github.com/Keddnyo/MiDoze"))

    val SCHAKAL = Intent(Intent.ACTION_VIEW,
        Uri.parse("https://4pda.to/forum/index.php?showuser=243484"))

}