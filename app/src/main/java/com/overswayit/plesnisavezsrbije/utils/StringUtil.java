package com.overswayit.plesnisavezsrbije.utils;

import androidx.annotation.StringRes;

import com.overswayit.plesnisavezsrbije.App;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class StringUtil {

    public static String getString(@StringRes int resId, Object... formatArgs) {
        return App.getContext().getString(resId, formatArgs);
    }

    public static String getHtmlFaker() {
        return "<tbody><tr>\n" +
                "            <td colspan=\"2\"><strong>Following fantastic and exciting Hip Hop and Breaking competitions in the 2013 World DanceSport Games in Kaohsiung (TPE), further to the 2018 World Youth Breaking Championship in Tokyo (JPN), and after making its successful Olympic debut at the 2018 Youth Olympic Games in Buenos Aires (ARG), the World DanceSport Federation continues to promote Breaking alongside with the other disciplines in the worldwide DanceSport family by announcing the WDSF World Breaking Championship to be organised in Nanjing (CHN) on June 23rd, 2019. The Championship will be held as one of the qualifier events for the first GAISF World Urban Games in Los Angeles (USA) in September 2019.<br>\n" +
                "              <br>\n" +
                "At the press conference held in Nanjing Hengda Conference and Exhibition Centre on January 18th, 2019, Mr. Ken Swift, one of the legends in Breaking, was present to answer the questions with his wide expertise pertaining to Breaking. The press conference was attended by many newspapers, TV channels and magazine publishers, and it was also streamed live. </strong></td>\n" +
                "          </tr>\n" +
                "          <tr> <td width=\"287\"><div align=\"center\"><a href=\"http://www.ples.co.rs/slike/vesti/2019/753d48da-3924-4ea6-8924-4f81c9152a62.jpg\" class=\"hoverZoomLink\"><img src=\"slike/vesti/2019/753d48da-3924-4ea6-8924-4f81c9152a62_resize.jpg\" width=\"256\" height=\"171\" alt=\"\"></a></div></td>\n" +
                "            <td width=\"403\"><strong>The 2019 WDSF World Breaking Championship is granted by the World DanceSport Federation, Social Sports Guidance Centre of the General Administration of Sport of China, and Chinese Dance Sport Federation. The local organisers are Social Sports Administration Centre of Jiangsu Province, Nanjing Sports Bureau, and Lishui District People’s Government of Nanjing City. </strong></td> \n" +
                "          </tr>\n" +
                "          </tbody>";
    }
}
