package io.rajeshp.adaptiveapp.data

object MenuRepo {
    object Dashboard {
        fun quickAccess() = getList("DB QA", 6)
        fun menu() = getList("DB Item", 7)
    }

    object Payments {
        fun quickAccess() = getList("QP", 3)
        fun menu() = getList("Payment Item", 7)
    }

    object ForYou {
        fun menu() = getList("FY Item", 4)
    }

    object More {
        fun quickAccess() = getList("MQA", 5)
        fun menu() = getList("Menu Item", 8).plus("Settings")
    }

    object Settings {
        fun menu() = getList("Setting", 9)
    }

    private fun getList(title: String, count: Int): List<String> {
        val list = mutableListOf<String>()
        for (i in 1..count) {
            list.add("$title $i")
        }
        return list
    }
}