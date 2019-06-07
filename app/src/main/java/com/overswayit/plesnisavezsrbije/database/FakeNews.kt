package com.overswayit.plesnisavezsrbije.database

import com.overswayit.plesnisavezsrbije.models.News

/**
 * Created by lazarristic on 2019-05-24.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object FakeNews {

    fun getNewsByTittle(title: String): News? {
        getAllNews().forEach {
            if (it.title?.toLowerCase().equals(title.toLowerCase())){
                return it
            }
        }

        return null
    }

    fun getAllNews(): List<News> {
        val news = createNews("Title", "02. februar 2019.", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
        val news2 = createNews("Title 22", "02. maj 2019.", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")

        return createListNews(news, news2)
    }

    private fun createNews(title: String, date: String, content: String): News {
        val news = News()
        news.title = title
        news.date = date
        news.content = content

        return news
    }

    private fun createListNews(vararg news: News): List<News> {
        val newsList = ArrayList<News>()

        news.forEach {
            newsList.add(it)
        }

        return newsList
    }
}