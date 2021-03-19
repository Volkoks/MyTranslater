package com.example.mytranslater.utils

import com.example.model.entites.Meanings
import com.example.model.entites.Translation
import com.example.model.entites.Word
import com.example.repository.datasource.room.HistoryWord

fun convertMeaningsToString(meanings: List<Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}

fun converterListWordToHistoryWord(list: List<Word>): HistoryWord? {
    val searchResult = list
    return if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
        null
    } else {
        HistoryWord(
            searchResult[0].text.toString(),
            searchResult[0].meanings?.get(0)?.translation?.translation,
            searchResult[0].id
        )
    }
}

fun convertHistoryWordToWord(list: List<HistoryWord>): List<Word> {
    val serchResult = ArrayList<Word>()


    if (!list.isNullOrEmpty()) {
        for (hystoryWord in list) {
            val meanings = ArrayList<Meanings>()
            val translation = Translation(hystoryWord.translate)
            meanings.add(Meanings(translation, null))
            serchResult.add(Word(hystoryWord.id, hystoryWord.word, meanings))
        }
    }
    return serchResult
}

