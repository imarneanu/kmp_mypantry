package com.icretu.mypantry.domain.repository

import com.icretu.mypantry.domain.model.UserSession
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    val session: Flow<UserSession?>
}
