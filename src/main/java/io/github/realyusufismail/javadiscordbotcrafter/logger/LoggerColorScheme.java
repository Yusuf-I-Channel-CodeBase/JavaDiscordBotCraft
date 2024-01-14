package io.github.realyusufismail.javadiscordbotcrafter.logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;
import org.jetbrains.annotations.NotNull;

public class LoggerColorScheme extends ForegroundCompositeConverterBase<ILoggingEvent> {
    @Override
    protected String getForegroundColorCode(@NotNull ILoggingEvent event) {
        return switch (event.getLevel().toInt()) {
            case Level.WARN_INT -> ANSIConstants.YELLOW_FG;
            case Level.DEBUG_INT -> ANSIConstants.CYAN_FG;
            case Level.INFO_INT -> ANSIConstants.BLUE_FG;
            case Level.ERROR_INT -> ANSIConstants.RED_FG;
            case Level.TRACE_INT -> ANSIConstants.MAGENTA_FG;
            default -> ANSIConstants.DEFAULT_FG;
        };
    }
}
