package eu.modernmt.lang;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by davide on 10/02/18.
 */
public class LanguageIndexTest {

    private LanguageIndex index;

    private static Language l(String s) {
        return Language.fromString(s);
    }

    private static LanguageDirection lp(String s) {
        String[] parts = s.split("\\s+");
        return new LanguageDirection(Language.fromString(parts[0]), Language.fromString(parts[1]));
    }

    private static List<LanguageDirection> list(String string) {
        ArrayList<LanguageDirection> result = new ArrayList<>();
        for (String part : string.split(","))
            result.add(lp(part.trim()));
        return result;
    }

    @Before
    public void setup() {
        index = new LanguageIndex.Builder()
                .add(lp("en it"))
                .add(lp("en fr"))
                .add(lp("fr en"))
                .add(lp("en es-ES"))
                .add(lp("en es-MX"))
                .add(lp("en sr-Latn"))
                .add(lp("en sr-Cyrl"))
                .add(lp("en zh-CN"))
                .add(lp("en zh-TW"))
                .add(lp("zh en"))

                .add(lp("en ru-AA"))
                .add(lp("en ru-BB"))
                .add(lp("es ru"))

                .addRule(l("es"), l("es-MX"))
                .addRule(l("sr"), l("sr-Latn"))
                .addRule(l("zh-SG"), l("zh-CN"))
                .addRule(l("zh-HK"), l("zh-TW"))
                .addRule(l("zh-MO"), l("zh-TW"))
                .addRule(l("zh"), l("zh-CN"))

                .build();
    }

    @Test
    public void simpleLanguageMonodirectional() {
        assertEquals(lp("en it"), index.map(lp("en it")));
        assertEquals(lp("en it"), index.map(lp("en it-IT")));
        assertEquals(lp("en it"), index.map(lp("en-US it")));
        assertEquals(lp("en it"), index.map(lp("en-US it-IT")));
        assertEquals(lp("en it"), index.map(lp("en-US it-IT-x-Custom")));

        assertNull(index.map(lp("it en")));
        assertNull(index.map(lp("it-IT en")));
        assertNull(index.map(lp("it en-US")));
        assertNull(index.map(lp("it-IT en-US")));
    }

    @Test
    public void simpleLanguageBidirectional() {
        assertEquals(lp("en fr"), index.map(lp("en fr")));
        assertEquals(lp("en fr"), index.map(lp("en fr-FR")));
        assertEquals(lp("en fr"), index.map(lp("en-GB fr")));
        assertEquals(lp("en fr"), index.map(lp("en-GB fr-FR")));

        assertEquals(lp("fr en"), index.map(lp("fr en")));
        assertEquals(lp("fr en"), index.map(lp("fr-FR en")));
        assertEquals(lp("fr en"), index.map(lp("fr en-GB")));
        assertEquals(lp("fr en"), index.map(lp("fr-FR en-GB")));
    }

    @Test
    public void ruledLanguageMonodirectional() {
        assertEquals(lp("en es-MX"), index.map(lp("en es")));
        assertEquals(lp("en es-ES"), index.map(lp("en es-ES")));
        assertEquals(lp("en es-MX"), index.map(lp("en es-MX")));
        assertEquals(lp("en es-MX"), index.map(lp("en es-CO")));
        assertEquals(lp("en es-MX"), index.map(lp("en-US es-CO")));
        assertEquals(lp("en es-MX"), index.map(lp("en-US-x-Custom es-CO-x-Custom")));

        assertNull(index.map(lp("es en")));
        assertNull(index.map(lp("es-ES en")));
        assertNull(index.map(lp("es-MX en")));
        assertNull(index.map(lp("es-CO en")));
        assertNull(index.map(lp("es-CO en-US")));
    }

    @Test
    public void ruledLanguageBidirectional() {
        assertEquals(lp("en zh-CN"), index.map(lp("en zh")));
        assertEquals(lp("en zh-CN"), index.map(lp("en zh-CN")));
        assertEquals(lp("en zh-CN"), index.map(lp("en zh-Hans-CN")));
        assertEquals(lp("en zh-CN"), index.map(lp("en zh-XX")));
        assertEquals(lp("en zh-TW"), index.map(lp("en zh-TW")));
        assertEquals(lp("en zh-TW"), index.map(lp("en zh-HK")));
        assertEquals(lp("en zh-TW"), index.map(lp("en-US zh-HK")));

        assertEquals(lp("zh en"), index.map(lp("zh en")));
        assertEquals(lp("zh en"), index.map(lp("zh-CN en")));
        assertEquals(lp("zh en"), index.map(lp("zh-XX en")));
        assertEquals(lp("zh en"), index.map(lp("zh-TW en")));
        assertEquals(lp("zh en"), index.map(lp("zh-HK en")));
        assertEquals(lp("zh en"), index.map(lp("zh-HK en-US")));
        assertEquals(lp("zh en"), index.map(lp("zh-Hans-HK en-Latn-US")));
    }

    @Test
    public void ruledLanguageWithScript() {
        assertEquals(lp("en sr-Latn"), index.map(lp("en sr")));
        assertEquals(lp("en sr-Latn"), index.map(lp("en sr-RS")));

        assertEquals(lp("en sr-Latn"), index.map(lp("en sr-Latn")));
        assertEquals(lp("en sr-Latn"), index.map(lp("en sr-Latn-RS")));
        assertEquals(lp("en sr-Cyrl"), index.map(lp("en sr-Cyrl")));
        assertEquals(lp("en sr-Cyrl"), index.map(lp("en sr-Cyrl-RS")));
        assertEquals(lp("en sr-Cyrl"), index.map(lp("en sr-Cyrl-RS-x-Custom")));
    }

    @Test
    public void inconsistentLanguageDialectSupport() {
        // If a target language is supported in a language pair,
        // the same target language could be not supported in all language pairs.
        assertEquals(lp("en ru-AA"), index.map(lp("en ru-AA")));
        assertEquals(lp("en ru-BB"), index.map(lp("en ru-BB")));
        assertEquals(lp("es ru"), index.map(lp("es ru-AA")));
        assertEquals(lp("es ru"), index.map(lp("es ru-BB")));
    }
}
