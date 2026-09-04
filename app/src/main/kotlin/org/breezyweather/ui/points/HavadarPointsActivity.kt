/*
 * This file is part of the Havadar fork (based on Breezy Weather, LGPL-3.0).
 */

package org.breezyweather.ui.points

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.breezyweather.common.activities.BreezyActivity
import org.breezyweather.domain.points.PointsManager
import org.breezyweather.ui.theme.compose.BreezyWeatherTheme

class HavadarPointsActivity : BreezyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pointsManager = PointsManager(this)
        pointsManager.onAppOpen()

        setContent {
            BreezyWeatherTheme {
                PointsScreen(pointsManager)
            }
        }
    }

    @Composable
    private fun PointsScreen(pointsManager: PointsManager) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "امتیازها و پاداش",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "امتیاز کل: ${pointsManager.totalPoints}",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "روزهای پیوسته: ${pointsManager.streakDays} — بهترین رکورد: ${pointsManager.bestStreak}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "هر روز که هوادار را باز کنی امتیاز می‌گیری؛ پشت سر هم باز کنی جایزهٔ بیشتری می‌گیری!",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            item {
                Text(
                    text = "نشان‌ها",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            items(pointsManager.badges().size) { index ->
                val badge = pointsManager.badges()[index]
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = if (badge.unlocked) "🏆" else "🔒",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Column(modifier = Modifier.padding(start = 12.dp)) {
                            Text(
                                text = badge.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = badge.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "جدول رتبه‌بندی: به‌زودی!",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "برای رقابت با دوستانت آماده می‌شویم. فعلاً امتیازها و نشان‌ها فقط روی گوشی خودت ذخیره می‌شوند — حتی بدون اینترنت هم کار می‌کنند.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
