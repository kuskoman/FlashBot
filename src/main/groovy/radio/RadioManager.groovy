package radio

import redis.RedisClientFactory
import groovy.json.*
import java.util.Base64

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

    static getRadioBackup() {
        def radios = getRadios()
        def json = new JsonBuilder(radios).toPrettyString()
        def base64Json = Base64.getEncoder().encodeToString(json.getBytes())

        return base64Json
    }

    static restoreRadioBackupJson(String base64Backup) {
        def json = new String(Base64.getDecoder().decode(base64Backup))
        def radios = new JsonSlurper().parseText(json)
        def map = redis.getMap(mapKey)
        removeAllRadios()
        radios.each { radio ->
            map.put(radio.key, radio.value)
        }
    }

    static removeAllRadios() {
        def map = redis.getMap(mapKey)
        map.clear()
    }

}
