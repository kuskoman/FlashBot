package player

import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import net.dv8tion.jda.api.entities.Guild
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioTrack
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

/* groovylint-disable-next-line LineLength */
// https://github.com/sedmelluq/lavaplayer/blob/master/demo-jda/src/main/java/com/sedmelluq/discord/lavaplayer/demo/jda/Main.java
class PlayerManager {

    private static final PlayerManager INSTANCE = new PlayerManager()
    private final musicManagers = new HashMap<String, GuildPlayer>()
    private final playerManager = new DefaultAudioPlayerManager()

    PlayerManager() {
        println 'Registering remote and local sources'
        AudioSourceManagers.registerRemoteSources(playerManager)
        AudioSourceManagers.registerLocalSource(playerManager)
    }

    public static PlayerManager getInstance() {
        return INSTANCE
    }

    public void loadAndPlay(MessageReceivedEvent event, String trackUrl) {
        println 'loadAndPlay'

        def guild = event.getGuild()
        def musicManager = getGuildAudioPlayer(guild)
        def player = musicManager.player
        connectToVoiceChannel(event)
        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {

            @Override
            void trackLoaded(AudioTrack track) {
                def trackValid = isValidHttpTrack(track)
                if (!trackValid) {
                    println 'Only HTTP tracks are supported'
                    notifyAboutError(guild, 'Only HTTP tracks are supported')
                    return
                }

                player.startTrack(track, false)
            }

            @Override
            void playlistLoaded(AudioPlaylist playlist) {
                notifyAboutError(guild, 'Playlists are not supported')
            }

            @Override
            void noMatches() {
                notifyAboutError(guild, "Nothing found by $trackUrl")
            }

            @Override
            void loadFailed(FriendlyException exception) {
                notifyAboutError(guild, "Could not play: $exception")
            }

        })
    }

    private GuildPlayer getGuildAudioPlayer(Guild guild) {
        def guildId = Long.parseLong(guild.getId())
        def musicManager = musicManagers.get(guildId)

        if (musicManager == null) {
            musicManager = new GuildPlayer(playerManager)
            musicManagers.put(guildId, musicManager)
        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler())

        return musicManager
    }

    private void notifyAboutError(MessageReceivedEvent event, String message) {
        event.getChannel().sendMessage(message).queue()
    }

    private void connectToVoiceChannel(MessageReceivedEvent event) {
        def audioManager = event.getGuild().getAudioManager()

        if (!event.getMember().getVoiceState().inAudioChannel()) {
            notifyAboutError(event, 'You need to be in a voice channel')
            return
        }

        def voiceChannel = event.getMember().getVoiceState().getChannel()
        audioManager.openAudioConnection(voiceChannel)
    }

    private static isValidHttpTrack(AudioTrack track) {
        return track instanceof HttpAudioTrack
    }

}
