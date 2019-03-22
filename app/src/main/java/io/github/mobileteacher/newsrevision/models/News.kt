package io.github.mobileteacher.newsrevision.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class News(var autor:String,
           var titulo:String,
           @SerializedName("noticia") var texto:String,
           var informativo:String,
           var data:String,
           @PrimaryKey var id:String="")

class NewsResponseObject(@SerializedName("noticias")
                         var news: List<News>)


//{
//    "noticias": [
//    {
//        "noticia": "Os novos advogados constituídos não têm conhecimento da totalidade da ação nem da tramitação dos autos, tornando assim necessário tempo para que possam estudar o feito e analisar situações de questões de ordem, regimental e processuais, disseram os advogados no documento.",
//        "autor": "teste autor",
//        "titulo": "MPF de São Paulo pede para investigar Alckmin na Lava Jato",
//        "id": "nfmw8",
//        "informativo": "teste informativo",
//        "data": "08/04/2018 21:59"
//    }
//    ]
//}