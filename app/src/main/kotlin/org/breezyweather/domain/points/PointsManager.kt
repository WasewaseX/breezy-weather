/*
 * This file is part of the Havadar fork (based on Breezy Weather, LGPL-3.0).
 */

package org.breezyweather.domain.points

import android.content.Context
import org.breezyweather.domain.settings.ConfigStore

/**
 * Havadar points & rewards engine — fully local, works without internet.
 * Leaderboard is planned ("soon"); until then everything stays on-device.
 */
class PointsManager(context: Context) {

    private val config = ConfigStore(context, "HAVADAR_POINTS")

    val totalPoints: Int
        get() = config.getInt(KEY_POINTS, 0)

    val streakDays: Int
        get() = config.getInt(KEY_STREAK, 0)

    val bestStreak: Int
        get() = config.getInt(KEY_BEST, 0)

    /** Call once per app open. Returns points gained today (0 if already counted). */
    fun onAppOpen(): Int {
        val today = System.currentTimeMillis() / DAY_MS
        val last = config.getLong(KEY_LAST_DAY, -1L)
        if (last == today) return 0

        val streak = if (last == today - 1) config.getInt(KEY_STREAK, 0) + 1 else 1
        val best = maxOf(streak, config.getInt(KEY_BEST, 0))
        val gained = 10 + minOf(streak, 7) * 2
        val total = config.getInt(KEY_POINTS, 0) + gained

        config.edit()
            .putLong(KEY_LAST_DAY, today)
            .putInt(KEY_STREAK, streak)
            .putInt(KEY_BEST, best)
            .putInt(KEY_POINTS, total)
            .apply()
        return gained
    }

    data class Badge(
        val title: String,
        val description: String,
        val unlocked: Boolean,
    )

    fun badges(): List<Badge> = listOf(
        Badge("شروع سفر", "اولین بازدید شما از هوادار", totalPoints > 0),
        Badge("سه روز پیوسته", "۳ روز پشت سر هم هوادار بودی", streakDays >= 3 || bestStreak >= 3),
        Badge("هفتهٔ هواداری", "۷ روز پشت سر هم", streakDays >= 7 || bestStreak >= 7),
        Badge("ماه هواداری", "۳۰ روز پشت سر هم", streakDays >= 30 || bestStreak >= 30),
        Badge("جمع‌آوری امتیاز", "۵۰۰ امتیاز یا بیشتر", totalPoints >= 500),
    )

    companion object {
        private const val DAY_MS = 86_400_000L
        private const val KEY_POINTS = "total_points"
        private const val KEY_STREAK = "streak_days"
        private const val KEY_BEST = "best_streak"
        private const val KEY_LAST_DAY = "last_open_day"
    }
}
