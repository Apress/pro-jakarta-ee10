package book.jakartapro.restdate.log4j;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.
    AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.*:
import org.apache.logging.log4j.spi.StandardLevel;

@Plugin(name = "StatisticsAppender", 
  category = Core.CATEGORY_NAME, 
  elementType = Appender.ELEMENT_TYPE)
public class StatisticsAppender extends AbstractAppender {
    public static class Stat {
        public int traceCnt = 0;
        public int debugCnt = 0;
        public int infoCnt = 0;
        public int warnCnt = 0;
        public int errorCnt = 0;
        public int allCnt() {
            return traceCnt + debugCnt + infoCnt + 
                warnCnt + errorCnt;
        }
    }
    private Map<LocalDate, Stat> hist = new HashMap<>();
    
    protected StatisticsAppender(String name, 
          Filter filter) {
        super(name, filter, null, true, 
              Property.EMPTY_ARRAY);
    }

    @PluginFactory
    public static StatisticsAppender createAppender(
          @PluginAttribute("name") String name,
          @PluginElement("Filter") Filter filter) {
        return new StatisticsAppender(name, filter);
    }

    @Override
    public void append(LogEvent event) {
        updateStat(event);
        maybeWriteOut();
    }

    private void maybeWriteOut() {
        // If we have just one entry for today, it means 
        // the last day just closed and can be written out
        LocalDate tm = LocalDate.now();
        // For testing and debugging, comment out the
        // following if()
        if(hist.get(tm).allCnt() == 1) {
            LocalDate tmx = tm.minusDays(1);
            // tmx = tm; // DEBUGGING ONLY
            if(hist.containsKey(tmx)) {
                Stat s = hist.get(tmx);
                System.out.println("STATISTICS - " + 
                          tmx.toString() + "\n" +
                        "all: " + s.allCnt() + "\n" +
                        "trace: " + s.traceCnt + "\n" +
                        "debug: " + s.debugCnt + "\n" +
                        "info: " + s.infoCnt + "\n" +
                        "warn: " + s.warnCnt + "\n" +
                        "error: " + s.errorCnt + "\n");
            }
            // Add some cleanup code here, removing old
            // entries from hist...
        }
    }

    private void updateStat(LogEvent event) {
        LocalDate tm = LocalDate.now();
        Stat stat = hist.computeIfAbsent(tm, 
            tm1 -> new Stat());
        int l = event.getLevel().intLevel();
        if(l == StandardLevel.TRACE.intLevel()) 
            stat.traceCnt++;
        else if(l == StandardLevel.DEBUG.intLevel()) 
            stat.debugCnt++;
        else if(l == StandardLevel.INFO.intLevel()) 
            stat.infoCnt++;
        else if(l == StandardLevel.WARN.intLevel()) 
            stat.warnCnt++;
        else if(l == StandardLevel.ERROR.intLevel()) 
            stat.errorCnt++;
        else if(l == StandardLevel.FATAL.intLevel()) 
            stat.errorCnt++;
    }
}
