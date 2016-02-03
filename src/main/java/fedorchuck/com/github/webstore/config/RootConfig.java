package fedorchuck.com.github.webstore.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import fedorchuck.com.github.webstore.config.RootConfig.WebPackage;

/**
 * Created by v on 28/01/16.
 */
@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages={"fedorchuck.com.github.webstore"},
        excludeFilters={
                @Filter(type=FilterType.CUSTOM, value=WebPackage.class)
        })
public class RootConfig {
    public static class WebPackage extends RegexPatternTypeFilter {
        public WebPackage() {
            super(Pattern.compile("webstore\\.web"));
        }
    }
}
