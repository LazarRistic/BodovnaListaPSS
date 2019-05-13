package com.overswayit.plesnisavezsrbije.events

/**
 * Created by lazarristic on 20/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object ViewMesssages {
    var clubContactPhoneCall = ClubContactPhoneCall()
    class ClubContactPhoneCall : Event<String>()

    var clubContactPhoneMessage = ClubContactPhoneMessage()
    class ClubContactPhoneMessage : Event<String>()

    var clubContactEmail = ClubContactEmail()
    class ClubContactEmail : Event<String>()
}
