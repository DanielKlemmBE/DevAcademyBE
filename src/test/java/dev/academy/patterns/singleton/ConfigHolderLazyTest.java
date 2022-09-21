package dev.academy.patterns.singleton;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ConfigHolderLazyTest {

    @Test
    public void getPoolSize() {
        assertThat(ConfigHolder.getInstance().getPoolSize()).isEqualTo(0);
        ConfigHolder.getInstance().setPoolSize(25);
        assertThat(ConfigHolder.getInstance().getPoolSize()).isEqualTo(25);
    }
}