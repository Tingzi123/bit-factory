package com.example.bitfactory.infrastructure.configuration;

import lombok.Getter;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

@Getter
public class RedissonConfig {
    private final RedissonClient redisson;

    public RedissonConfig() {
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("redis://127.0.0.1:6379");
        this.redisson = Redisson.create(config);
    }
}
