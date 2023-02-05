package radio

import redis.RedisClientFactory
import groovy.json.JsonBuilder

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
        def json = new JsonBuilder(radios).toPrettyString()

        return json
    }

}
