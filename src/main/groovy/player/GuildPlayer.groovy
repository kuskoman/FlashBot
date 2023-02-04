package player

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager

public class GuildPlayer {

    private AudioPlayer player

    public GuildPlayer(AudioPlayerManager manager) {
        player = manager.createPlayer()
    }

    public AudioPlayerSendHandler getSendHandler() {
        return new AudioPlayerSendHandler(player)
    }

}
