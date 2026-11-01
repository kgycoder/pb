package com.sync.app.glass

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kyant.backdrop.Backdrop
import com.sync.app.glass.kyant.LiquidButton

/**
 * Now Playing 의 캡슐 버튼(닫기 · 전체화면 · 가사 화면 닫기).
 * DOM 버튼은 위치/상태의 원천으로만 남고(투명), 유리 표면과 터치는 Kyant [LiquidButton] 이 담당한다.
 * 기본 LiquidButton 의 pop-out(눌림 확대 + 드래그 변형 + 하이라이트)을 그대로 사용한다.
 */
@Composable
internal fun NpButtonsGlass(buttons: List<NpBtn>, backdrop: Backdrop, c: GlassController) {
    val accent = LocalGlassAccent.current
    buttons.forEach { b ->
        key(b.kind) {
            Box(Modifier.cssRect(b.rect)) {
                LiquidButton(
                    onClick = { c.cmd { put("t", "np"); put("a", b.kind) } },
                    backdrop = backdrop,
                    modifier = Modifier.fillMaxSize(),
                    surfaceColor = GlassTheme.Container,
                    contentPadding = 0.dp
                ) {
                    when (b.kind) {
                        "x" -> Canvas(Modifier.size(13.dp)) {
                            strokeIcon(GlassIcons.NpClose, 13f, Color.White.copy(0.85f), 1.9f)
                        }
                        "fs" -> Canvas(Modifier.size(16.dp)) {
                            strokeIcon(GlassIcons.NpExpand, 16f, if (b.on) accent else Color.White.copy(0.85f), 1.8f)
                        }
                        else -> Canvas(Modifier.size(15.dp)) {
                            strokeIcon(GlassIcons.NpChevronDown, 15f, Color.White.copy(0.9f), 2f)
                        }
                    }
                }
            }
        }
    }
}
