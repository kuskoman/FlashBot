/* groovylint-disable VariableTypeRequired */
package redis

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config

import utils.EnvUtils

class RedisClientFactory {

    private static RedissonClient client

    static RedissonClient getClient() {
        def redisUrl = EnvUtils.getEnv('REDIS_URL')
        def config = new Config()
        config.useSingleServer().setAddress(redisUrl)
        if (client == null) {
            client = Redisson.create(config)
        }
        return client
    }

}
