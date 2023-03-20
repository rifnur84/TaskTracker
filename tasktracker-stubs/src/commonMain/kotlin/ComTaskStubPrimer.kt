package ru.rifnur.tasktracker.stubs

import ru.rifnur.tasktracker.common.models.*

object ComTaskStubPrimer {
    val Task_Zadanie1 : ComTask
        get() = ComTask(
            id = ComTaskId("1"),
            title = "Задача 1. Решить уравнение",
            description = "Требуется умножить 5 на 25",
            ownerId = ComTaskUserId("user-1"),
//            adType = MkplDealSide.DEMAND,
            visibility = ComTaskVisibility.VISIBLE_PUBLIC,
            permissionsClient = mutableSetOf(
                ComTaskPermissionClient.READ,
                ComTaskPermissionClient.UPDATE,
                ComTaskPermissionClient.DELETE,
                ComTaskPermissionClient.MAKE_VISIBLE_PUBLIC,
                ComTaskPermissionClient.MAKE_VISIBLE_GROUP,
                ComTaskPermissionClient.MAKE_VISIBLE_OWNER,
            )
        )

}
