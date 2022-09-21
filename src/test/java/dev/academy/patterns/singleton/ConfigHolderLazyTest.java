package dev.academy.patterns.singleton;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ConfigHolderLazyTest {

    @Test
    public void getPoolSize() {
        assertThat(ConfigHolderLazy.getInstance().getPoolSize()).isEqualTo(0);
        ConfigHolderLazy.getInstance().setPoolSize(25);
        assertThat(ConfigHolderLazy.getInstance().getPoolSize()).isEqualTo(25);
    }
}