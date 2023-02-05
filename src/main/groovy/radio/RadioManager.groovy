package radio

import redis.RedisClientFactory

class RadioManager {

    private static redis = RedisClientFactory.getClient()
    private static mapKey = 'radios'

    static addRadio(String name, String url) {
        def map = redis.getMap(mapKey)
        map.put(name, url)
    }

    static removeRadio(String name) {
        def map = redis.getMap(mapKey)
        map.remove(name)
    }

    static getRadios() {
        def map = redis.getMap(mapKey)
        return map.readAllMap()
    }

    static getRadioUrl(String name) {
        def map = redis.getMap(mapKey)
        return map.get(name)
    }

    static getRadioBackupJson() {
        def radios = getRadios()

        return {
            radios.each { radio ->
                radio.key(radio.key)
                radio.value(radio.value)
            }
        }.toString()
    }

}
