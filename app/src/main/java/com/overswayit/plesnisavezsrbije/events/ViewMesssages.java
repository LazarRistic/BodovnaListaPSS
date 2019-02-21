package com.overswayit.plesnisavezsrbije.events;

/**
 * Created by lazarristic on 20/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class ViewMesssages {
    public static class ClubContactPhoneCall extends Event<String> { protected ClubContactPhoneCall() {} }
    public static ClubContactPhoneCall clubContactPhoneCall = new ClubContactPhoneCall();

    public static class ClubContactPhoneMessage extends Event<String> { protected ClubContactPhoneMessage() {} }
    public static ClubContactPhoneMessage clubContactPhoneMessage = new ClubContactPhoneMessage();

    public static class ClubContactEmail extends Event<String> { protected ClubContactEmail() {} }
    public static ClubContactEmail clubContactEmail = new ClubContactEmail();
}
