package com.icretu.mypantry.core.session

import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    val session: Flow<UserSession?>
}
