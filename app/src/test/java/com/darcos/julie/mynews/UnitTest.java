package com.darcos.julie.mynews;

import com.darcos.julie.mynews.Activities.ResultSearch;
import com.darcos.julie.mynews.Activities.SearchActivity;
import com.darcos.julie.mynews.Models.ArticleList;
import com.darcos.julie.mynews.Models.Search.Search;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {


    @Test
    public void convertDateTest() {
        String date = "18/01/2018";

        assertEquals("20180118", ResultSearch.converDate(date));

    }

    @Test
    public void dateTodayTest() {
        String date = "25/03/2019";
        assertEquals(date, SearchActivity.dateToday());
    }

    @Test
    public void dateArticleTest() {
        String date = "2019-03-22T08:30:05-04:00";
        assertEquals("22/03/19", ArticleList.date(date));
    }

}